package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK224;

public final class BigEndianOffsetKECCAK224 extends BigEndianOffsetHash implements KECCAK224 {

  public BigEndianOffsetKECCAK224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof KECCAK224
        && ((Hash) other).equals(data, offset);
  }
}