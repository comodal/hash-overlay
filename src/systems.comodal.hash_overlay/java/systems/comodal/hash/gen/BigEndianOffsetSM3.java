package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SM3;

public final class BigEndianOffsetSM3 extends BigEndianOffsetHash implements SM3 {

  public BigEndianOffsetSM3(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SM3
        && ((Hash) other).equals(data, offset);
  }
}