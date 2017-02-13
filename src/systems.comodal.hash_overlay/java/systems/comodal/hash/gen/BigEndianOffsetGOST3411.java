package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.GOST3411;

public final class BigEndianOffsetGOST3411 extends BigEndianOffsetHash implements GOST3411 {

  public BigEndianOffsetGOST3411(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof GOST3411
        && ((Hash) other).equals(data, offset);
  }
}