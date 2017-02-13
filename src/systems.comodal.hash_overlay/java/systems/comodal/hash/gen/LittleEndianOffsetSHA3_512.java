package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_512;

public final class LittleEndianOffsetSHA3_512 extends LittleEndianOffsetHash implements SHA3_512 {

  public LittleEndianOffsetSHA3_512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA3_512
        && ((Hash) other).equalsReverse(data, offset);
  }
}