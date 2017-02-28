package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA384;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class SHA384Value extends DiscreteHash implements SHA384 {

  public SHA384Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SHA384> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA384
        && ((Hash) other).digestEquals(data);
  }
}