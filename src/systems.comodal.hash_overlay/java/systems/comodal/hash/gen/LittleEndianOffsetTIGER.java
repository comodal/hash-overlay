package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.TIGER;

public final class LittleEndianOffsetTIGER extends LittleEndianOffsetHash implements TIGER {

  public LittleEndianOffsetTIGER(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof TIGER
        && ((Hash) other).equalsReverse(data, offset);
  }
}