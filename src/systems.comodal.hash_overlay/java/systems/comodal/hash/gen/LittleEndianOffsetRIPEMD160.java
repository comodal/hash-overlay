package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD160;

public final class LittleEndianOffsetRIPEMD160 extends LittleEndianOffsetHash implements RIPEMD160 {

  public LittleEndianOffsetRIPEMD160(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof RIPEMD160
        && ((Hash) other).equalsReverse(data, offset);
  }
}