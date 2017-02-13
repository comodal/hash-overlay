package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B256;

public final class LittleEndianOffsetBLAKE2B256 extends LittleEndianOffsetHash implements BLAKE2B256 {

  public LittleEndianOffsetBLAKE2B256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof BLAKE2B256
        && ((Hash) other).equalsReverse(data, offset);
  }
}