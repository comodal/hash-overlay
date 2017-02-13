package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Sha256;

final class LittleEndianOffsetSha256 extends LittleEndianOffset256 implements Sha256 {

  LittleEndianOffsetSha256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Sha256
        && ((Hash) other).equalsReverse(data, offset);
  }
}
