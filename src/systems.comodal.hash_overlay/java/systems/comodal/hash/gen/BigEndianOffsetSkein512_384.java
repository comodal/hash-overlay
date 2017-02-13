package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_384;

public final class BigEndianOffsetSkein512_384 extends BigEndianOffsetHash implements Skein512_384 {

  public BigEndianOffsetSkein512_384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein512_384
        && ((Hash) other).equals(data, offset);
  }
}