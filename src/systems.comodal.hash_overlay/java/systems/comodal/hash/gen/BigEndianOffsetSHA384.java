package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA384;

public final class BigEndianOffsetSHA384 extends BigEndianOffsetHash implements SHA384 {

  public BigEndianOffsetSHA384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA384
        && ((Hash) other).equals(data, offset);
  }
}