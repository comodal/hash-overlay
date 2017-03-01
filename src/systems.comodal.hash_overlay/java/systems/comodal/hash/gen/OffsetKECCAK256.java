package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetKECCAK256 extends OffsetHash implements KECCAK256 {

  public OffsetKECCAK256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<KECCAK256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK256
        && ((Hash) other).digestEquals(data, offset);
  }
}