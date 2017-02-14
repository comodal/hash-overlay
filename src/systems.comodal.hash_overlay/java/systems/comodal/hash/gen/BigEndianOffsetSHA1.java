package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA1;
import systems.comodal.hash.base.BigEndianOffsetHash;

public final class BigEndianOffsetSHA1 extends BigEndianOffsetHash implements SHA1 {

  public BigEndianOffsetSHA1(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA1
        && ((Hash) other).equals(data, offset);
  }
}