package systems.comodal.hash.base;

abstract class Discrete256 extends DiscreteHash {

  Discrete256(final byte[] data) {
    super(data);
  }

  @Override
  public int hashCode() {
    return (data[31] & 0xFF)
        | (data[30] & 0xFF) << 8
        | (data[29] & 0xFF) << 16
        | (data[28] & 0xFF) << 24;
  }
}
