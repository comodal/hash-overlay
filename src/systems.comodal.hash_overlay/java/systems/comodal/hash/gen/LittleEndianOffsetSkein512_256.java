package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_256;

public final class LittleEndianOffsetSkein512_256 extends LittleEndianOffsetHash implements Skein512_256 {

  public LittleEndianOffsetSkein512_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein512_256
        && ((Hash) other).equalsReverse(data, offset);
  }
}