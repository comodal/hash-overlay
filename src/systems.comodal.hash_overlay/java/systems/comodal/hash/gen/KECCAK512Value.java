package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK512;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.DiscreteHash;

public final class KECCAK512Value extends DiscreteHash implements KECCAK512 {

  public KECCAK512Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<KECCAK512> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK512
        && ((Hash) other).equals(data);
  }
}