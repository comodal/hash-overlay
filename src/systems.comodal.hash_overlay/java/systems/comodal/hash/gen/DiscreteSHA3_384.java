package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_384;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteSHA3_384 extends DiscreteHash implements SHA3_384 {

  public DiscreteSHA3_384(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SHA3_384> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA3_384
        && ((Hash) other).equals(data);
  }
}