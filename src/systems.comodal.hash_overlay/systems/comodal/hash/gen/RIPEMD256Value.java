package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class RIPEMD256Value extends HashValue implements RIPEMD256 {

  public RIPEMD256Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<RIPEMD256> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD256
        && ((Hash) other).digestEquals(data);
  }
}