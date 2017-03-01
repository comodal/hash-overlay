package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_224;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetSHA3_224 extends OffsetHash implements SHA3_224 {

  public OffsetSHA3_224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA3_224> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA3_224
        && ((Hash) other).digestEquals(data, offset);
  }
}