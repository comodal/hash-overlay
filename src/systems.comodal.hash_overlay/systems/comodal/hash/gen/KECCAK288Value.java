package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK288;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class KECCAK288Value extends HashValue implements KECCAK288 {

  public KECCAK288Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<KECCAK288> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK288
        && ((Hash) other).digestEquals(data);
  }
}