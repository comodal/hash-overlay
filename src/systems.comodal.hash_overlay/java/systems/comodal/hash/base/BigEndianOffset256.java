package systems.comodal.hash.base;

abstract class BigEndianOffset256 extends BigEndianOffsetHash {

  BigEndianOffset256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public int hashCode() {
    int offset = this.offset + 31;
    return (data[offset] & 0xFF)
        | (data[--offset] & 0xFF) << 8
        | (data[--offset] & 0xFF) << 16
        | (data[--offset] & 0xFF) << 24;
  }
}
