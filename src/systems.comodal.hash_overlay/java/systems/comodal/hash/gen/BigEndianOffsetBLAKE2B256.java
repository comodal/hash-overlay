package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B256;

public final class BigEndianOffsetBLAKE2B256 extends BigEndianOffsetHash implements BLAKE2B256 {

  public BigEndianOffsetBLAKE2B256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof BLAKE2B256
        && ((Hash) other).equals(data, offset);
  }
}