package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class KECCAK256Value extends HashValue implements KECCAK256 {

  public KECCAK256Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<KECCAK256> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK256
        && ((Hash) other).digestEquals(data);
  }
}