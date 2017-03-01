package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK512;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetKECCAK512 extends OffsetHash implements KECCAK512 {

  public OffsetKECCAK512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<KECCAK512> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK512
        && ((Hash) other).digestEquals(data, offset);
  }
}