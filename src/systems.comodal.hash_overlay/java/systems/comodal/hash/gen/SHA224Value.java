package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA224;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class SHA224Value extends DiscreteHash implements SHA224 {

  public SHA224Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SHA224> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA224
        && ((Hash) other).digestEquals(data);
  }
}