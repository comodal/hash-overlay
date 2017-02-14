package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetSHA512 extends LittleEndianOffsetHash implements SHA512 {

  public LittleEndianOffsetSHA512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA512
        && ((Hash) other).equalsReverse(data, offset);
  }
}