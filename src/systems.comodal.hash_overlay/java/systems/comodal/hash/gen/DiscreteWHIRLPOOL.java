package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.WHIRLPOOL;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteWHIRLPOOL extends DiscreteHash implements WHIRLPOOL {

  public DiscreteWHIRLPOOL(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<WHIRLPOOL> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof WHIRLPOOL
        && ((Hash) other).equals(data);
  }
}