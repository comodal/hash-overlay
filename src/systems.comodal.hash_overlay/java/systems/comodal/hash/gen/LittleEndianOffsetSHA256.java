package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA256;

public final class LittleEndianOffsetSHA256 extends LittleEndianOffsetHash implements SHA256 {

  public LittleEndianOffsetSHA256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA256
        && ((Hash) other).equalsReverse(data, offset);
  }
}