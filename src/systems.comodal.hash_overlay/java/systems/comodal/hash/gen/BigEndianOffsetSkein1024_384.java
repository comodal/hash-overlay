package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_384;

public final class BigEndianOffsetSkein1024_384 extends BigEndianOffsetHash implements Skein1024_384 {

  public BigEndianOffsetSkein1024_384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein1024_384
        && ((Hash) other).equals(data, offset);
  }
}