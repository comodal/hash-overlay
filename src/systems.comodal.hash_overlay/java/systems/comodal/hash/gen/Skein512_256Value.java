package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class Skein512_256Value extends DiscreteHash implements Skein512_256 {

  public Skein512_256Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<Skein512_256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein512_256
        && ((Hash) other).equals(data);
  }
}