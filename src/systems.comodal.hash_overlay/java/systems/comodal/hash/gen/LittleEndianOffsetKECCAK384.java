package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK384;

public final class LittleEndianOffsetKECCAK384 extends LittleEndianOffsetHash implements KECCAK384 {

  public LittleEndianOffsetKECCAK384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof KECCAK384
        && ((Hash) other).equalsReverse(data, offset);
  }
}