package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.GOST3411_2012_256;

public final class BigEndianOffsetGOST3411_2012_256 extends BigEndianOffsetHash implements GOST3411_2012_256 {

  public BigEndianOffsetGOST3411_2012_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof GOST3411_2012_256
        && ((Hash) other).equals(data, offset);
  }
}