package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B512;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class BLAKE2B512Value extends HashValue implements BLAKE2B512 {

  public BLAKE2B512Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<BLAKE2B512> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof BLAKE2B512
        && ((Hash) other).digestEquals(data);
  }
}