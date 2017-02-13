package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD320;

public final class BigEndianOffsetRIPEMD320 extends BigEndianOffsetHash implements RIPEMD320 {

  public BigEndianOffsetRIPEMD320(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof RIPEMD320
        && ((Hash) other).equals(data, offset);
  }
}