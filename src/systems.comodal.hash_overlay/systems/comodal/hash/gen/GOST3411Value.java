package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.GOST3411;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class GOST3411Value extends HashValue implements GOST3411 {

  public GOST3411Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<GOST3411> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof GOST3411
        && ((Hash) other).digestEquals(data);
  }
}