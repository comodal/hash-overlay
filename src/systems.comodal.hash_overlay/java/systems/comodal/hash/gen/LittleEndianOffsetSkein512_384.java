package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_384;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetSkein512_384 extends LittleEndianOffsetHash implements Skein512_384 {

  public LittleEndianOffsetSkein512_384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein512_384
        && ((Hash) other).equalsReverse(data, offset);
  }
}