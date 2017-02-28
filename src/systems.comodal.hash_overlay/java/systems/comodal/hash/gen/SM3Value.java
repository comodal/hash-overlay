package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SM3;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class SM3Value extends HashValue implements SM3 {

  public SM3Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SM3> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SM3
        && ((Hash) other).digestEquals(data);
  }
}