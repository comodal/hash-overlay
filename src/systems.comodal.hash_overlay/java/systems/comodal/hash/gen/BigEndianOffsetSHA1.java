package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA1;

public final class BigEndianOffsetSHA1 extends BigEndianOffsetHash implements SHA1 {

  public BigEndianOffsetSHA1(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA1
        && ((Hash) other).equals(data, offset);
  }
}