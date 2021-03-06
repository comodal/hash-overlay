package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_384;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class Skein512_384Value extends HashValue implements Skein512_384 {

  public Skein512_384Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<Skein512_384> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein512_384
        && ((Hash) other).digestEquals(data);
  }
}