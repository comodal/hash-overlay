package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA224;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetSHA224 extends OffsetHash implements SHA224 {

  public OffsetSHA224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA224> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA224
        && ((Hash) other).digestEquals(data, offset);
  }
}