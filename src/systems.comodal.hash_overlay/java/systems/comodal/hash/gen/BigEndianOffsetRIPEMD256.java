package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD256;

public final class BigEndianOffsetRIPEMD256 extends BigEndianOffsetHash implements RIPEMD256 {

  public BigEndianOffsetRIPEMD256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof RIPEMD256
        && ((Hash) other).equals(data, offset);
  }
}