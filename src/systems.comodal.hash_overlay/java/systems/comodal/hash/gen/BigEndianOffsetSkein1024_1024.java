package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_1024;

public final class BigEndianOffsetSkein1024_1024 extends BigEndianOffsetHash implements Skein1024_1024 {

  public BigEndianOffsetSkein1024_1024(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein1024_1024
        && ((Hash) other).equals(data, offset);
  }
}