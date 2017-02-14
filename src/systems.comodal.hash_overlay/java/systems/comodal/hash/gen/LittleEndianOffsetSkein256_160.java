package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_160;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetSkein256_160 extends LittleEndianOffsetHash implements Skein256_160 {

  public LittleEndianOffsetSkein256_160(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein256_160
        && ((Hash) other).equalsReverse(data, offset);
  }
}