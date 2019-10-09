/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package confluo.rpc;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-10-09")
public enum rpc_data_type implements org.apache.thrift.TEnum {
  RPC_NONE(0),
  RPC_BOOL(1),
  RPC_CHAR(2),
  RPC_UCHAR(3),
  RPC_SHORT(4),
  RPC_USHORT(5),
  RPC_INT(6),
  RPC_UINT(7),
  RPC_LONG(8),
  RPC_ULONG(9),
  RPC_FLOAT(10),
  RPC_DOUBLE(11),
  RPC_STRING(12),
  RPC_RECORD(10001),
  RPC_ALERT(10002);

  private final int value;

  private rpc_data_type(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static rpc_data_type findByValue(int value) { 
    switch (value) {
      case 0:
        return RPC_NONE;
      case 1:
        return RPC_BOOL;
      case 2:
        return RPC_CHAR;
      case 3:
        return RPC_UCHAR;
      case 4:
        return RPC_SHORT;
      case 5:
        return RPC_USHORT;
      case 6:
        return RPC_INT;
      case 7:
        return RPC_UINT;
      case 8:
        return RPC_LONG;
      case 9:
        return RPC_ULONG;
      case 10:
        return RPC_FLOAT;
      case 11:
        return RPC_DOUBLE;
      case 12:
        return RPC_STRING;
      case 10001:
        return RPC_RECORD;
      case 10002:
        return RPC_ALERT;
      default:
        return null;
    }
  }
}
