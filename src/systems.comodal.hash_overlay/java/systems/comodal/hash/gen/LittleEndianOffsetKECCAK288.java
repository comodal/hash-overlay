package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK288;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetKECCAK288 extends LittleEndianOffsetHash implements KECCAK288 {

  public LittleEndianOffsetKECCAK288(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK288
        && ((Hash) other).equalsReverse(data, offset);
  }
}