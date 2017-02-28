package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class SHAValue extends DiscreteHash implements SHA {

  public SHAValue(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SHA> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA
        && ((Hash) other).digestEquals(data);
  }
}