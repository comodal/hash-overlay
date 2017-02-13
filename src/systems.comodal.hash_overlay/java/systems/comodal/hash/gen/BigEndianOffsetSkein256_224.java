package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_224;

public final class BigEndianOffsetSkein256_224 extends BigEndianOffsetHash implements Skein256_224 {

  public BigEndianOffsetSkein256_224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein256_224
        && ((Hash) other).equals(data, offset);
  }
}