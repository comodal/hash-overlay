package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_512;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class Skein1024_512Value extends DiscreteHash implements Skein1024_512 {

  public Skein1024_512Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<Skein1024_512> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein1024_512
        && ((Hash) other).digestEquals(data);
  }
}