package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetSHA512 extends OffsetHash implements SHA512 {

  public OffsetSHA512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA512> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA512
        && ((Hash) other).digestEquals(data, offset);
  }
}