package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD4;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class MD4Value extends DiscreteHash implements MD4 {

  public MD4Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<MD4> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD4
        && ((Hash) other).equals(data);
  }
}