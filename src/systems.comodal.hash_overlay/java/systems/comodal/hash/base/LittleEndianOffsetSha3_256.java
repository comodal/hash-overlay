package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Sha3_256;

public final class LittleEndianOffsetSha3_256 extends LittleEndianOffsetHash implements Sha3_256 {

  public LittleEndianOffsetSha3_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Sha3_256
        && ((Hash) other).equalsReverse(data, offset);
  }
}