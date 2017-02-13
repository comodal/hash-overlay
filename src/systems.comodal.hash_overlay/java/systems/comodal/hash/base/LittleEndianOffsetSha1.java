package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Sha1;

public final class LittleEndianOffsetSha1 extends LittleEndianOffsetHash implements Sha1 {

  public LittleEndianOffsetSha1(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Sha1
        && ((Hash) other).equalsReverse(data, offset);
  }
}