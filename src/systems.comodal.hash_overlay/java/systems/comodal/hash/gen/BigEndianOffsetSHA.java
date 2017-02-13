package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA;

public final class BigEndianOffsetSHA extends BigEndianOffsetHash implements SHA {

  public BigEndianOffsetSHA(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA
        && ((Hash) other).equals(data, offset);
  }
}