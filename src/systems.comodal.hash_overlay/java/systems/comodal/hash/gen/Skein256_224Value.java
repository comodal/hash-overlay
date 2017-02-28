package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_224;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class Skein256_224Value extends HashValue implements Skein256_224 {

  public Skein256_224Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<Skein256_224> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein256_224
        && ((Hash) other).digestEquals(data);
  }
}