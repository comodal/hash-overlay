package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_224;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetSHA3_224 extends LittleEndianOffsetHash implements SHA3_224 {

  public LittleEndianOffsetSHA3_224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA3_224
        && ((Hash) other).equalsReverse(data, offset);
  }
}