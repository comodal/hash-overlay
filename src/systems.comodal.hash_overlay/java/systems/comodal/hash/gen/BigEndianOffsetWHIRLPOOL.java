package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.WHIRLPOOL;

public final class BigEndianOffsetWHIRLPOOL extends BigEndianOffsetHash implements WHIRLPOOL {

  public BigEndianOffsetWHIRLPOOL(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof WHIRLPOOL
        && ((Hash) other).equals(data, offset);
  }
}