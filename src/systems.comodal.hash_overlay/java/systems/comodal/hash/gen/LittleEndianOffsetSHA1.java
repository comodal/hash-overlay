package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA1;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetSHA1 extends LittleEndianOffsetHash implements SHA1 {

  public LittleEndianOffsetSHA1(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA1
        && ((Hash) other).equalsReverse(data, offset);
  }
}