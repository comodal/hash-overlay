package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD160;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteRIPEMD160 extends DiscreteHash implements RIPEMD160 {

  public DiscreteRIPEMD160(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<RIPEMD160> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD160
        && ((Hash) other).equals(data);
  }
}