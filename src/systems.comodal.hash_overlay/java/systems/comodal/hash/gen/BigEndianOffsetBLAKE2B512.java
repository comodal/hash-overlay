package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B512;

public final class BigEndianOffsetBLAKE2B512 extends BigEndianOffsetHash implements BLAKE2B512 {

  public BigEndianOffsetBLAKE2B512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof BLAKE2B512
        && ((Hash) other).equals(data, offset);
  }
}