package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD128;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class RIPEMD128Value extends DiscreteHash implements RIPEMD128 {

  public RIPEMD128Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<RIPEMD128> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD128
        && ((Hash) other).digestEquals(data);
  }
}