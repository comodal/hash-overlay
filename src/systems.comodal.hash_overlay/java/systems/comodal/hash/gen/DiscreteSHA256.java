package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteSHA256 extends DiscreteHash implements SHA256 {

  public DiscreteSHA256(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SHA256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA256
        && ((Hash) other).equals(data);
  }
}