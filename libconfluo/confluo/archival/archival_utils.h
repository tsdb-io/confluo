#ifndef CONFLUO_ARCHIVAL_ARCHIVAL_UTILS_H_
#define CONFLUO_ARCHIVAL_ARCHIVAL_UTILS_H_

#include "encoder.h"
#include "incr_file_writer.h"
#include "container/reflog.h"

namespace confluo {
namespace archival {

using namespace ::utils;
using namespace ::confluo::storage;

class archival_utils {
 public:
  typedef storage::read_only_encoded_ptr<uint64_t> bucket_ptr_t;
  typedef bucket_ptr_t::decoded_ptr decoded_ptr_t;
  typedef encoder::raw_encoded_ptr raw_encoded_ptr_t;
  typedef encoded_ptr<uint64_t> encoded_ptr_t;

  /**
   * Archive buckets of a monolog_linear until a given offset.
   * @param monolog monolog_linear to archive
   * @param writer archival output
   * @param start monolog offset to start archival from
   * @param stop monolog offset to end archival at
   * @return monolog offset archived up to
   */
  template<typename T, size_t MAX_BUCKETS, size_t BUCKET_SIZE, size_t BUFFER_SIZE, encoding_type ENCODING>
  static size_t archive_monolog_linear(monolog_linear<T, MAX_BUCKETS, BUCKET_SIZE, BUFFER_SIZE>& monolog,
                                       incremental_file_writer& writer, size_t start, size_t stop) {
    // TODO replace with bucket iterator later
    size_t archival_tail = start;
    storage::read_only_encoded_ptr<T> bucket_ptr;
    while (archival_tail < stop) {
      monolog.ptr(archival_tail, bucket_ptr);
      auto decoded_ptr = bucket_ptr.decode_ptr();
      auto* metadata = storage::ptr_metadata::get(bucket_ptr.get().ptr());
      T* data = decoded_ptr.get();

      size_t encoded_size;
      encoder::raw_encoded_ptr raw_encoded_bucket = encoder::encode<T, ENCODING>(data, encoded_size);
      auto off = writer.append<ptr_metadata, uint8_t>(metadata, 1, raw_encoded_bucket.get(), encoded_size);
      writer.update_metadata(archival_tail);
      writer.flush();

      void* encoded_bucket = ALLOCATOR.mmap(off.path(), off.offset(), encoded_size, state_type::D_ARCHIVED);
      monolog.swap_bucket_ptr(archival_tail / BUCKET_SIZE, storage::encoded_ptr<T>(encoded_bucket));
      archival_tail += BUCKET_SIZE;
    }
    return archival_tail;
  }

  /**
   * Archives a reflog.
   * @param reflog aggregated_reflog to archive
   * @param writer archival output
   * @param start reflog offset to start archival from
   * @param data_log_stop data log offset to archive up until
   * @return reflog offset archived up to
   */
  template<encoding_type ENCODING>
  static size_t archive_reflog(reflog& reflog, incremental_file_writer& writer, size_t start,
                               size_t data_log_stop) {
    bucket_ptr_t bucket_ptr;
    size_t archival_tail = start;
    size_t data_log_archival_tail = 0;
    // TODO replace w/ iterator
    while (data_log_archival_tail < data_log_stop && archival_tail < reflog.size()) {
      reflog.ptr(archival_tail, bucket_ptr);
      decoded_ptr_t decoded_ptr = bucket_ptr.decode_ptr();
      auto* metadata = ptr_metadata::get(bucket_ptr.get().ptr());
      uint64_t* data = decoded_ptr.get();

      if (metadata->state_ != state_type::D_IN_MEMORY) {
        archival_tail += reflog_constants::BUCKET_SIZE;
        continue;
      }

      data_log_archival_tail = max_in_reflog_bucket(data);
      if (data_log_archival_tail >= data_log_stop) {
        break;
      }

      size_t encoded_size;
      raw_encoded_ptr_t raw_encoded_bucket = encoder::encode<uint64_t, ENCODING>(data, encoded_size);
      auto off = writer.append<ptr_metadata, uint8_t>(metadata, 1, raw_encoded_bucket.get(), encoded_size);
      writer.update_metadata(data_log_archival_tail);
      writer.flush();

      void* encoded_bucket = ALLOCATOR.mmap(off.path(), off.offset(), encoded_size, state_type::D_ARCHIVED);
      reflog.swap_bucket_ptr(archival_tail, encoded_ptr_t(encoded_bucket));
      archival_tail += reflog_constants::BUCKET_SIZE;
    }
    return archival_tail;
  }

  /**
   * Archive aggregates of an aggregated reflog.
   * @param key
   * @param reflog aggregated reflog
   * @param writer writer
   * @param version version to get aggregates for
   */
  static void archive_reflog_aggregates(aggregated_reflog& reflog, incremental_file_writer& writer,
                                        size_t version) {
    size_t num_aggs = reflog.num_aggregates();
    numeric* collapsed_aggregates = new numeric[num_aggs];
    for (size_t i = 0; i < num_aggs; i++) {
      collapsed_aggregates[i] = reflog.get_aggregate(i, version);
    }
    writer.append<numeric>(collapsed_aggregates, num_aggs);
    writer.flush();

    size_t alloc_size = sizeof(aggregate) * num_aggs;
    aggregate* archived_aggs = static_cast<aggregate*>(ALLOCATOR.alloc(alloc_size, state_type::D_ARCHIVED));
    for (size_t i = 0; i < num_aggs; i++) {
      new (archived_aggs + i) aggregate(collapsed_aggregates[i].type(), D_SUM, 1);
      archived_aggs[i].update(0, collapsed_aggregates[i], version);
    }
    reflog.swap_aggregates(archived_aggs);
    delete[] collapsed_aggregates;
  }

 private:
  static uint64_t max_in_reflog_bucket(uint64_t* data) {
    uint64_t max = 0;
    for (size_t i = 0; i < reflog_constants::BUCKET_SIZE && data[i] != (uint64_t) -1; i++)
      max = std::max(max, data[i]);
    return max;
  }

};

}
}

#endif /* CONFLUO_ARCHIVAL_ARCHIVAL_UTILS_H_ */
