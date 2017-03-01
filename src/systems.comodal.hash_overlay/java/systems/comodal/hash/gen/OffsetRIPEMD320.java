package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD320;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetRIPEMD320 extends OffsetHash implements RIPEMD320 {

  public OffsetRIPEMD320(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<RIPEMD320> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD320
        && ((Hash) other).digestEquals(data, offset);
  }
}