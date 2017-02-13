package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512_256;

public final class BigEndianOffsetSHA512_256 extends BigEndianOffsetHash implements SHA512_256 {

  public BigEndianOffsetSHA512_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA512_256
        && ((Hash) other).equals(data, offset);
  }
}