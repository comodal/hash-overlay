package systems.comodal.hash.base;

abstract class LittleEndianOffset256 extends LittleEndianOffsetHash {

  LittleEndianOffset256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public int hashCode() {
    return (data[0] & 0xFF)
        | (data[1] & 0xFF) << 8
        | (data[2] & 0xFF) << 16
        | (data[3] & 0xFF) << 24;
  }
}
