package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class Skein256_256Value extends HashValue implements Skein256_256 {

  public Skein256_256Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<Skein256_256> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein256_256
        && ((Hash) other).digestEquals(data);
  }
}