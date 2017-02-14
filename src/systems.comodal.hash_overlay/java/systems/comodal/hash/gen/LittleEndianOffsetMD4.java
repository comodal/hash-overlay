package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD4;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetMD4 extends LittleEndianOffsetHash implements MD4 {

  public LittleEndianOffsetMD4(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD4
        && ((Hash) other).equalsReverse(data, offset);
  }
}