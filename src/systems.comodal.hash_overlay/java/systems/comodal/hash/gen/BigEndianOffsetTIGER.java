package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.TIGER;

public final class BigEndianOffsetTIGER extends BigEndianOffsetHash implements TIGER {

  public BigEndianOffsetTIGER(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof TIGER
        && ((Hash) other).equals(data, offset);
  }
}