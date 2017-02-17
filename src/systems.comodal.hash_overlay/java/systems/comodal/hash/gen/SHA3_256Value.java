package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class SHA3_256Value extends DiscreteHash implements SHA3_256 {

  public SHA3_256Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SHA3_256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA3_256
        && ((Hash) other).equals(data);
  }
}