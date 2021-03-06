package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.TIGER;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class TIGERValue extends HashValue implements TIGER {

  public TIGERValue(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<TIGER> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof TIGER
        && ((Hash) other).digestEquals(data);
  }
}