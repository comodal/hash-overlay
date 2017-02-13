package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA;

public final class LittleEndianOffsetSHA extends LittleEndianOffsetHash implements SHA {

  public LittleEndianOffsetSHA(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA
        && ((Hash) other).equalsReverse(data, offset);
  }
}