package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Md5;

public final class BigEndianOffsetMd5 extends BigEndianOffsetHash implements Md5 {

  public BigEndianOffsetMd5(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Md5
        && ((Hash) other).equals(data, offset);
  }
}