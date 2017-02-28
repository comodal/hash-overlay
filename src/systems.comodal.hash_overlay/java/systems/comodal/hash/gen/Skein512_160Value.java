package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_160;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class Skein512_160Value extends HashValue implements Skein512_160 {

  public Skein512_160Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<Skein512_160> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein512_160
        && ((Hash) other).digestEquals(data);
  }
}