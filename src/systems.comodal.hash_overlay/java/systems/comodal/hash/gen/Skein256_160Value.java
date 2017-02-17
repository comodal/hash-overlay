package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_160;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class Skein256_160Value extends DiscreteHash implements Skein256_160 {

  public Skein256_160Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<Skein256_160> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein256_160
        && ((Hash) other).equals(data);
  }
}