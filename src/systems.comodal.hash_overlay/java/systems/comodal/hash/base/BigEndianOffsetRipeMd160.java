package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.RipeMd160;

public final class BigEndianOffsetRipeMd160 extends BigEndianOffsetHash implements RipeMd160 {

  public BigEndianOffsetRipeMd160(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof RipeMd160
        && ((Hash) other).equals(data, offset);
  }
}
