package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_512;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class SHA3_512Value extends HashValue implements SHA3_512 {

  public SHA3_512Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SHA3_512> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA3_512
        && ((Hash) other).digestEquals(data);
  }
}