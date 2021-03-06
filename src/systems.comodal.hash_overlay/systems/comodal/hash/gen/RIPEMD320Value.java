package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD320;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class RIPEMD320Value extends HashValue implements RIPEMD320 {

  public RIPEMD320Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<RIPEMD320> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD320
        && ((Hash) other).digestEquals(data);
  }
}