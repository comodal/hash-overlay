package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD4;

public final class BigEndianOffsetMD4 extends BigEndianOffsetHash implements MD4 {

  public BigEndianOffsetMD4(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof MD4
        && ((Hash) other).equals(data, offset);
  }
}