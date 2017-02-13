package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK256;

public final class BigEndianOffsetKECCAK256 extends BigEndianOffsetHash implements KECCAK256 {

  public BigEndianOffsetKECCAK256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof KECCAK256
        && ((Hash) other).equals(data, offset);
  }
}