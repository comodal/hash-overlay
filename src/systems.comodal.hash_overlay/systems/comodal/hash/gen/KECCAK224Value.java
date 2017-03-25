package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK224;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class KECCAK224Value extends HashValue implements KECCAK224 {

  public KECCAK224Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<KECCAK224> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK224
        && ((Hash) other).digestEquals(data);
  }
}