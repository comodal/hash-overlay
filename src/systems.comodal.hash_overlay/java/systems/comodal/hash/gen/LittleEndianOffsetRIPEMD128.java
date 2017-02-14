package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD128;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetRIPEMD128 extends LittleEndianOffsetHash implements RIPEMD128 {

  public LittleEndianOffsetRIPEMD128(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD128
        && ((Hash) other).equalsReverse(data, offset);
  }
}