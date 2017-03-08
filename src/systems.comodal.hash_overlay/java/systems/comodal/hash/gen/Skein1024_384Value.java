package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_384;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class Skein1024_384Value extends HashValue implements Skein1024_384 {

  public Skein1024_384Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<Skein1024_384> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein1024_384
        && ((Hash) other).digestEquals(data);
  }
}