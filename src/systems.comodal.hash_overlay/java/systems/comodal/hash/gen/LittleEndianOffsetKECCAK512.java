package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK512;

public final class LittleEndianOffsetKECCAK512 extends LittleEndianOffsetHash implements KECCAK512 {

  public LittleEndianOffsetKECCAK512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof KECCAK512
        && ((Hash) other).equalsReverse(data, offset);
  }
}