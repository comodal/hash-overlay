package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_224;

public final class BigEndianOffsetSkein512_224 extends BigEndianOffsetHash implements Skein512_224 {

  public BigEndianOffsetSkein512_224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein512_224
        && ((Hash) other).equals(data, offset);
  }
}