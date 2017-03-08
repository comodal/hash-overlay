package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class BLAKE2B256Value extends HashValue implements BLAKE2B256 {

  public BLAKE2B256Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<BLAKE2B256> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof BLAKE2B256
        && ((Hash) other).digestEquals(data);
  }
}