package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK512;

public final class BigEndianOffsetKECCAK512 extends BigEndianOffsetHash implements KECCAK512 {

  public BigEndianOffsetKECCAK512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof KECCAK512
        && ((Hash) other).equals(data, offset);
  }
}