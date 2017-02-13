package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK224;

public final class LittleEndianOffsetKECCAK224 extends LittleEndianOffsetHash implements KECCAK224 {

  public LittleEndianOffsetKECCAK224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof KECCAK224
        && ((Hash) other).equalsReverse(data, offset);
  }
}