package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_256;

public final class BigEndianOffsetSHA3_256 extends BigEndianOffsetHash implements SHA3_256 {

  public BigEndianOffsetSHA3_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA3_256
        && ((Hash) other).equals(data, offset);
  }
}