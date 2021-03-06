package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD160;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class RIPEMD160Value extends HashValue implements RIPEMD160 {

  public RIPEMD160Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<RIPEMD160> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD160
        && ((Hash) other).digestEquals(data);
  }
}