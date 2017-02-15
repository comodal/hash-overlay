package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteRIPEMD256 extends DiscreteHash implements RIPEMD256 {

  public DiscreteRIPEMD256(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<RIPEMD256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD256
        && ((Hash) other).equals(data);
  }
}