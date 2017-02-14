package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD2;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetMD2 extends LittleEndianOffsetHash implements MD2 {

  public LittleEndianOffsetMD2(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD2
        && ((Hash) other).equalsReverse(data, offset);
  }
}