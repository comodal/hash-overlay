package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_256;
import systems.comodal.hash.base.BigEndianOffsetHash;

public final class BigEndianOffsetSkein256_256 extends BigEndianOffsetHash implements Skein256_256 {

  public BigEndianOffsetSkein256_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein256_256
        && ((Hash) other).equals(data, offset);
  }
}