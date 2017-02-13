package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_256;

public final class BigEndianOffsetSkein512_256 extends BigEndianOffsetHash implements Skein512_256 {

  public BigEndianOffsetSkein512_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein512_256
        && ((Hash) other).equals(data, offset);
  }
}