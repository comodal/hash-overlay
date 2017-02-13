package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD5;

public final class BigEndianOffsetMD5 extends BigEndianOffsetHash implements MD5 {

  public BigEndianOffsetMD5(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof MD5
        && ((Hash) other).equals(data, offset);
  }
}