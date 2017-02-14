package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_1024;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetSkein1024_1024 extends LittleEndianOffsetHash implements Skein1024_1024 {

  public LittleEndianOffsetSkein1024_1024(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein1024_1024
        && ((Hash) other).equalsReverse(data, offset);
  }
}