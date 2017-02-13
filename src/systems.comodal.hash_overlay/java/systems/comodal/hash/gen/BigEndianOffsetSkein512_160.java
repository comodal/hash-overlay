package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_160;

public final class BigEndianOffsetSkein512_160 extends BigEndianOffsetHash implements Skein512_160 {

  public BigEndianOffsetSkein512_160(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein512_160
        && ((Hash) other).equals(data, offset);
  }
}