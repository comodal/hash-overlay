package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA224;

public final class LittleEndianOffsetSHA224 extends LittleEndianOffsetHash implements SHA224 {

  public LittleEndianOffsetSHA224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA224
        && ((Hash) other).equalsReverse(data, offset);
  }
}