package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512;

public final class BigEndianOffsetSHA512 extends BigEndianOffsetHash implements SHA512 {

  public BigEndianOffsetSHA512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA512
        && ((Hash) other).equals(data, offset);
  }
}