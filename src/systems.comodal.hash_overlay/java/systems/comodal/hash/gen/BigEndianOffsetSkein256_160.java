package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_160;
import systems.comodal.hash.base.BigEndianOffsetHash;

public final class BigEndianOffsetSkein256_160 extends BigEndianOffsetHash implements Skein256_160 {

  public BigEndianOffsetSkein256_160(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein256_160
        && ((Hash) other).equals(data, offset);
  }
}