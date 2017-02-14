package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK288;
import systems.comodal.hash.base.BigEndianOffsetHash;

public final class BigEndianOffsetKECCAK288 extends BigEndianOffsetHash implements KECCAK288 {

  public BigEndianOffsetKECCAK288(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK288
        && ((Hash) other).equals(data, offset);
  }
}