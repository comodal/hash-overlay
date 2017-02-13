package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_256;

public final class LittleEndianOffsetSkein256_256 extends LittleEndianOffsetHash implements Skein256_256 {

  public LittleEndianOffsetSkein256_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein256_256
        && ((Hash) other).equalsReverse(data, offset);
  }
}