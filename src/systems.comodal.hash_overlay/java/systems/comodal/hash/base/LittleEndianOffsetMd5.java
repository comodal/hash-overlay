package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Md5;

public final class LittleEndianOffsetMd5 extends LittleEndianOffsetHash implements Md5 {

  public LittleEndianOffsetMd5(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Md5
        && ((Hash) other).equalsReverse(data, offset);
  }
}
