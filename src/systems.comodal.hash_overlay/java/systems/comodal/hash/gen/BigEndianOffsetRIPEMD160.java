package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD160;

public final class BigEndianOffsetRIPEMD160 extends BigEndianOffsetHash implements RIPEMD160 {

  public BigEndianOffsetRIPEMD160(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof RIPEMD160
        && ((Hash) other).equals(data, offset);
  }
}