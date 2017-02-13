package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Sha256;

public final class BigEndianOffsetSha256 extends BigEndianOffsetHash implements Sha256 {

  public BigEndianOffsetSha256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Sha256
        && ((Hash) other).equals(data, offset);
  }
}