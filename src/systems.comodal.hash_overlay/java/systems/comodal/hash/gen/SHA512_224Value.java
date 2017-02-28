package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512_224;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class SHA512_224Value extends DiscreteHash implements SHA512_224 {

  public SHA512_224Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SHA512_224> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA512_224
        && ((Hash) other).digestEquals(data);
  }
}