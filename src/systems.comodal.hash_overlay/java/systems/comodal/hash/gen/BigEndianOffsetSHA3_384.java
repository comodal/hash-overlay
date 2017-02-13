package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_384;

public final class BigEndianOffsetSHA3_384 extends BigEndianOffsetHash implements SHA3_384 {

  public BigEndianOffsetSHA3_384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA3_384
        && ((Hash) other).equals(data, offset);
  }
}