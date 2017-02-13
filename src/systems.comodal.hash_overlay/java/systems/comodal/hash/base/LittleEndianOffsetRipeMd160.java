package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.RipeMd160;

public final class LittleEndianOffsetRipeMd160 extends LittleEndianOffsetHash implements RipeMd160 {

  public LittleEndianOffsetRipeMd160(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof RipeMd160
        && ((Hash) other).equalsReverse(data, offset);
  }
}
