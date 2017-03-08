package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD5;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class MD5Value extends HashValue implements MD5 {

  public MD5Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<MD5> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD5
        && ((Hash) other).digestEquals(data);
  }
}