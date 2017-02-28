package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.WHIRLPOOL;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetWHIRLPOOL extends LittleEndianOffsetHash implements WHIRLPOOL {

  public LittleEndianOffsetWHIRLPOOL(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<WHIRLPOOL> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof WHIRLPOOL
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}