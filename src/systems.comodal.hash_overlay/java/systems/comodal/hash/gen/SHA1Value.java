package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA1;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class SHA1Value extends DiscreteHash implements SHA1 {

  public SHA1Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SHA1> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA1
        && ((Hash) other).equals(data);
  }
}