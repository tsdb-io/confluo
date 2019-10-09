/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package confluo.rpc;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-10-09")
public class rpc_record_block implements org.apache.thrift.TBase<rpc_record_block, rpc_record_block._Fields>, java.io.Serializable, Cloneable, Comparable<rpc_record_block> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("rpc_record_block");

  private static final org.apache.thrift.protocol.TField TIME_BLOCK_FIELD_DESC = new org.apache.thrift.protocol.TField("time_block", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("data", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField NRECORDS_FIELD_DESC = new org.apache.thrift.protocol.TField("nrecords", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new rpc_record_blockStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new rpc_record_blockTupleSchemeFactory();

  private long time_block; // required
  private @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer data; // required
  private long nrecords; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TIME_BLOCK((short)1, "time_block"),
    DATA((short)2, "data"),
    NRECORDS((short)3, "nrecords");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TIME_BLOCK
          return TIME_BLOCK;
        case 2: // DATA
          return DATA;
        case 3: // NRECORDS
          return NRECORDS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TIME_BLOCK_ISSET_ID = 0;
  private static final int __NRECORDS_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TIME_BLOCK, new org.apache.thrift.meta_data.FieldMetaData("time_block", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.DATA, new org.apache.thrift.meta_data.FieldMetaData("data", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    tmpMap.put(_Fields.NRECORDS, new org.apache.thrift.meta_data.FieldMetaData("nrecords", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(rpc_record_block.class, metaDataMap);
  }

  public rpc_record_block() {
  }

  public rpc_record_block(
    long time_block,
    java.nio.ByteBuffer data,
    long nrecords)
  {
    this();
    this.time_block = time_block;
    setTimeBlockIsSet(true);
    this.data = org.apache.thrift.TBaseHelper.copyBinary(data);
    this.nrecords = nrecords;
    setNrecordsIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public rpc_record_block(rpc_record_block other) {
    __isset_bitfield = other.__isset_bitfield;
    this.time_block = other.time_block;
    if (other.isSetData()) {
      this.data = org.apache.thrift.TBaseHelper.copyBinary(other.data);
    }
    this.nrecords = other.nrecords;
  }

  public rpc_record_block deepCopy() {
    return new rpc_record_block(this);
  }

  @Override
  public void clear() {
    setTimeBlockIsSet(false);
    this.time_block = 0;
    this.data = null;
    setNrecordsIsSet(false);
    this.nrecords = 0;
  }

  public long getTimeBlock() {
    return this.time_block;
  }

  public rpc_record_block setTimeBlock(long time_block) {
    this.time_block = time_block;
    setTimeBlockIsSet(true);
    return this;
  }

  public void unsetTimeBlock() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TIME_BLOCK_ISSET_ID);
  }

  /** Returns true if field time_block is set (has been assigned a value) and false otherwise */
  public boolean isSetTimeBlock() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TIME_BLOCK_ISSET_ID);
  }

  public void setTimeBlockIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TIME_BLOCK_ISSET_ID, value);
  }

  public byte[] getData() {
    setData(org.apache.thrift.TBaseHelper.rightSize(data));
    return data == null ? null : data.array();
  }

  public java.nio.ByteBuffer bufferForData() {
    return org.apache.thrift.TBaseHelper.copyBinary(data);
  }

  public rpc_record_block setData(byte[] data) {
    this.data = data == null ? (java.nio.ByteBuffer)null   : java.nio.ByteBuffer.wrap(data.clone());
    return this;
  }

  public rpc_record_block setData(@org.apache.thrift.annotation.Nullable java.nio.ByteBuffer data) {
    this.data = org.apache.thrift.TBaseHelper.copyBinary(data);
    return this;
  }

  public void unsetData() {
    this.data = null;
  }

  /** Returns true if field data is set (has been assigned a value) and false otherwise */
  public boolean isSetData() {
    return this.data != null;
  }

  public void setDataIsSet(boolean value) {
    if (!value) {
      this.data = null;
    }
  }

  public long getNrecords() {
    return this.nrecords;
  }

  public rpc_record_block setNrecords(long nrecords) {
    this.nrecords = nrecords;
    setNrecordsIsSet(true);
    return this;
  }

  public void unsetNrecords() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __NRECORDS_ISSET_ID);
  }

  /** Returns true if field nrecords is set (has been assigned a value) and false otherwise */
  public boolean isSetNrecords() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __NRECORDS_ISSET_ID);
  }

  public void setNrecordsIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __NRECORDS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case TIME_BLOCK:
      if (value == null) {
        unsetTimeBlock();
      } else {
        setTimeBlock((java.lang.Long)value);
      }
      break;

    case DATA:
      if (value == null) {
        unsetData();
      } else {
        if (value instanceof byte[]) {
          setData((byte[])value);
        } else {
          setData((java.nio.ByteBuffer)value);
        }
      }
      break;

    case NRECORDS:
      if (value == null) {
        unsetNrecords();
      } else {
        setNrecords((java.lang.Long)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case TIME_BLOCK:
      return getTimeBlock();

    case DATA:
      return getData();

    case NRECORDS:
      return getNrecords();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case TIME_BLOCK:
      return isSetTimeBlock();
    case DATA:
      return isSetData();
    case NRECORDS:
      return isSetNrecords();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof rpc_record_block)
      return this.equals((rpc_record_block)that);
    return false;
  }

  public boolean equals(rpc_record_block that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_time_block = true;
    boolean that_present_time_block = true;
    if (this_present_time_block || that_present_time_block) {
      if (!(this_present_time_block && that_present_time_block))
        return false;
      if (this.time_block != that.time_block)
        return false;
    }

    boolean this_present_data = true && this.isSetData();
    boolean that_present_data = true && that.isSetData();
    if (this_present_data || that_present_data) {
      if (!(this_present_data && that_present_data))
        return false;
      if (!this.data.equals(that.data))
        return false;
    }

    boolean this_present_nrecords = true;
    boolean that_present_nrecords = true;
    if (this_present_nrecords || that_present_nrecords) {
      if (!(this_present_nrecords && that_present_nrecords))
        return false;
      if (this.nrecords != that.nrecords)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(time_block);

    hashCode = hashCode * 8191 + ((isSetData()) ? 131071 : 524287);
    if (isSetData())
      hashCode = hashCode * 8191 + data.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(nrecords);

    return hashCode;
  }

  @Override
  public int compareTo(rpc_record_block other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetTimeBlock()).compareTo(other.isSetTimeBlock());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimeBlock()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.time_block, other.time_block);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetData()).compareTo(other.isSetData());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetData()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.data, other.data);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetNrecords()).compareTo(other.isSetNrecords());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNrecords()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nrecords, other.nrecords);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("rpc_record_block(");
    boolean first = true;

    sb.append("time_block:");
    sb.append(this.time_block);
    first = false;
    if (!first) sb.append(", ");
    sb.append("data:");
    if (this.data == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.data, sb);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("nrecords:");
    sb.append(this.nrecords);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'time_block' because it's a primitive and you chose the non-beans generator.
    if (data == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'data' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'nrecords' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class rpc_record_blockStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public rpc_record_blockStandardScheme getScheme() {
      return new rpc_record_blockStandardScheme();
    }
  }

  private static class rpc_record_blockStandardScheme extends org.apache.thrift.scheme.StandardScheme<rpc_record_block> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, rpc_record_block struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TIME_BLOCK
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.time_block = iprot.readI64();
              struct.setTimeBlockIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.data = iprot.readBinary();
              struct.setDataIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // NRECORDS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.nrecords = iprot.readI64();
              struct.setNrecordsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetTimeBlock()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'time_block' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetNrecords()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'nrecords' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, rpc_record_block struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(TIME_BLOCK_FIELD_DESC);
      oprot.writeI64(struct.time_block);
      oprot.writeFieldEnd();
      if (struct.data != null) {
        oprot.writeFieldBegin(DATA_FIELD_DESC);
        oprot.writeBinary(struct.data);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(NRECORDS_FIELD_DESC);
      oprot.writeI64(struct.nrecords);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class rpc_record_blockTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public rpc_record_blockTupleScheme getScheme() {
      return new rpc_record_blockTupleScheme();
    }
  }

  private static class rpc_record_blockTupleScheme extends org.apache.thrift.scheme.TupleScheme<rpc_record_block> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, rpc_record_block struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI64(struct.time_block);
      oprot.writeBinary(struct.data);
      oprot.writeI64(struct.nrecords);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, rpc_record_block struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.time_block = iprot.readI64();
      struct.setTimeBlockIsSet(true);
      struct.data = iprot.readBinary();
      struct.setDataIsSet(true);
      struct.nrecords = iprot.readI64();
      struct.setNrecordsIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

