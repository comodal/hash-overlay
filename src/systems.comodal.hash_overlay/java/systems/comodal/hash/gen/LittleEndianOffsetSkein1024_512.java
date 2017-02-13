package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_512;

public final class LittleEndianOffsetSkein1024_512 extends LittleEndianOffsetHash implements Skein1024_512 {

  public LittleEndianOffsetSkein1024_512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein1024_512
        && ((Hash) other).equalsReverse(data, offset);
  }
}