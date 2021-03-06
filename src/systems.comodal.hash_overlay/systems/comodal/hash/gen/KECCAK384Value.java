package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK384;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class KECCAK384Value extends HashValue implements KECCAK384 {

  public KECCAK384Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<KECCAK384> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK384
        && ((Hash) other).digestEquals(data);
  }
}