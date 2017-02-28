package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD128;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BigEndianOffsetHash;

public final class BigEndianOffsetRIPEMD128 extends BigEndianOffsetHash implements RIPEMD128 {

  public BigEndianOffsetRIPEMD128(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<RIPEMD128> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD128
        && ((Hash) other).digestEquals(data, offset);
  }
}