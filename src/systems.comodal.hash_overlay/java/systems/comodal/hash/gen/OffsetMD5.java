package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD5;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetMD5 extends OffsetHash implements MD5 {

  public OffsetMD5(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<MD5> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD5
        && ((Hash) other).digestEquals(data, offset);
  }
}