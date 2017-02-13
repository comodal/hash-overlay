package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B384;

public final class BigEndianOffsetBLAKE2B384 extends BigEndianOffsetHash implements BLAKE2B384 {

  public BigEndianOffsetBLAKE2B384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof BLAKE2B384
        && ((Hash) other).equals(data, offset);
  }
}