package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class SHAValue extends HashValue implements SHA {

  public SHAValue(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<SHA> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA
        && ((Hash) other).digestEquals(data);
  }
}