#ifndef CONFLUO_STORAGE_STORAGE_ALLOCATOR_H_
#define CONFLUO_STORAGE_STORAGE_ALLOCATOR_H_

#include <unistd.h>

#include "conf/configuration_params.h"
#include "exceptions.h"
#include "memory_stat.h"
#include "mmap_utils.h"
#include "ptr_metadata.h"

namespace confluo {
namespace storage {

using namespace ::utils;
// TODO: delegate requests to memory pools

/**
 * Storage allocator class. Interface for managing memory and storage
 */
class storage_allocator {

 public:
  /**
   * Initializes a storage allocator.
   */
  storage_allocator()
      : mem_stat_(),
        mmap_stat_() {
  }

  /**
   * Allocates memory and metadata for an object.
   * @param size size in bytes to allocate
   * @return pointer to allocated memory
   */
  void* alloc(size_t size, uint8_t state = state_type::D_IN_MEMORY) {
    if (mem_stat_.get() >= configuration_params::MAX_MEMORY) {
      THROW(memory_exception, "Max memory reached!");
    }
    size_t alloc_size = sizeof(ptr_metadata) + size;
    mem_stat_.increment(alloc_size);

    // allocate contiguous memory for both the ptr and metadata
    void* ptr = malloc(alloc_size);
    ptr_metadata* md = new (ptr) ptr_metadata;
    void* data_ptr = reinterpret_cast<void*>(md + 1);

    md->alloc_type_ = alloc_type::D_DEFAULT;
    md->state_ = state_type::D_IN_MEMORY;
    md->data_size_ = size;
    md->offset_ = 0;

    return data_ptr;
  }

  /**
   * Allocates new memory backed by file. Creates the file, overwriting old
   * data if the file already existed.
   *
   * @param path backing file
   * @param size size to allocate
   * @param state pointer state (bit field, constrained to storage::state_type)
   * @return pointer to memory
   */
  void* mmap(std::string path, size_t size, uint8_t state = state_type::D_IN_MEMORY) {
    size_t alloc_size = sizeof(ptr_metadata) + size;
    mmap_stat_.increment(alloc_size);

    int fd = utils::file_utils::open_file(path, O_CREAT | O_TRUNC | O_RDWR);
    file_utils::truncate_file(fd, alloc_size);
    void* ptr = mmap_utils::map(fd, nullptr, 0, alloc_size);
    file_utils::close_file(fd);

    storage::ptr_metadata* metadata = static_cast<ptr_metadata*>(ptr);
    metadata->alloc_type_ = alloc_type::D_MMAP;
    metadata->state_ = state;
    metadata->data_size_ = size;
    metadata->offset_ = 0;

    return reinterpret_cast<void*>(metadata + 1);
  }

  /**
   * Memory-maps part of an existing file.
   *
   * @param path path of file
   * @param offset file offset (does not need to be page aligned)
   * @param size size to allocate
   * @param state pointer state (bit field, constrained to storage::state_type)
   * @return pointer to memory
   */
  void* mmap(std::string path, off_t offset, size_t size, uint8_t state) {
    int mmap_delta = offset % getpagesize();
    off_t page_aligned_offset = offset - mmap_delta;

    size_t mmap_size = sizeof(ptr_metadata) + size + mmap_delta;
    mmap_stat_.increment(mmap_size);

    int fd = file_utils::open_file(path, O_RDWR);
    uint8_t* ptr = static_cast<uint8_t*>(mmap_utils::map(fd, nullptr, page_aligned_offset, mmap_size));

    ptr += mmap_delta;
    file_utils::close_file(fd);

    storage::ptr_metadata* metadata = reinterpret_cast<ptr_metadata*>(ptr);
    metadata->alloc_type_ = alloc_type::D_MMAP;
    metadata->state_ = state;
    metadata->data_size_ = size;
    metadata->offset_ = mmap_delta;

    return reinterpret_cast<void*>(metadata + 1);
  }

  /**
   * Deallocate or unmap pointer returned by this allocator.
   * @param ptr pointer to memory
   */
  void dealloc(void* ptr) {
    ptr_metadata* md = ptr_metadata::get(ptr);
    size_t alloc_size = sizeof(ptr_metadata) + md->data_size_ + md->offset_;
    switch (md->alloc_type_) {
    case alloc_type::D_DEFAULT:
      md->~ptr_metadata();
      free(md);
      mem_stat_.decrement(alloc_size);
      break;
    case alloc_type::D_MMAP:
      size_t total_offset = ptr_metadata::get(ptr)->offset_ + sizeof(ptr_metadata);
      mmap_utils::unmap(reinterpret_cast<uint8_t*>(ptr) - total_offset, alloc_size);
      mmap_stat_.decrement(alloc_size);
      break;
    }
  }

  /**
   * Gets the memory utilization stats
   *
   * @return The utilization of memory
   */
  size_t memory_utilization() {
    return mem_stat_.get();
  }

 private:
  memory_stat mem_stat_;
  memory_stat mmap_stat_;

};

}
}

#endif /* CONFLUO_STORAGE_STORAGE_ALLOCATOR_H_ */
