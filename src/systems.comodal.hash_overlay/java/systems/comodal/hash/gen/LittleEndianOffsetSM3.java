package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SM3;

public final class LittleEndianOffsetSM3 extends LittleEndianOffsetHash implements SM3 {

  public LittleEndianOffsetSM3(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SM3
        && ((Hash) other).equalsReverse(data, offset);
  }
}