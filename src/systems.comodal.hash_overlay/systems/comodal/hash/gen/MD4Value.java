package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD4;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class MD4Value extends HashValue implements MD4 {

  public MD4Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<MD4> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD4
        && ((Hash) other).digestEquals(data);
  }
}