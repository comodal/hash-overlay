package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B160;

public final class BigEndianOffsetBLAKE2B160 extends BigEndianOffsetHash implements BLAKE2B160 {

  public BigEndianOffsetBLAKE2B160(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof BLAKE2B160
        && ((Hash) other).equals(data, offset);
  }
}