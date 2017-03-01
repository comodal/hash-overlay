package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA1;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetSHA1 extends OffsetHash implements SHA1 {

  public OffsetSHA1(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA1> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA1
        && ((Hash) other).digestEquals(data, offset);
  }
}