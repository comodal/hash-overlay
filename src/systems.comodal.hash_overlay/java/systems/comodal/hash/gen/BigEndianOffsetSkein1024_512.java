package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_512;
import systems.comodal.hash.base.BigEndianOffsetHash;

public final class BigEndianOffsetSkein1024_512 extends BigEndianOffsetHash implements Skein1024_512 {

  public BigEndianOffsetSkein1024_512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein1024_512
        && ((Hash) other).equals(data, offset);
  }
}