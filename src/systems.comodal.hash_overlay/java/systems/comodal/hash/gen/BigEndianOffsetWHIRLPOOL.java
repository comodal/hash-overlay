package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.WHIRLPOOL;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BigEndianOffsetHash;

public final class BigEndianOffsetWHIRLPOOL extends BigEndianOffsetHash implements WHIRLPOOL {

  public BigEndianOffsetWHIRLPOOL(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<WHIRLPOOL> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof WHIRLPOOL
        && ((Hash) other).equals(data, offset);
  }
}