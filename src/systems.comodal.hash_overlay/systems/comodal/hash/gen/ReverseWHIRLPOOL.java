package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.WHIRLPOOL;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseWHIRLPOOL extends ReverseHash implements WHIRLPOOL {

  public ReverseWHIRLPOOL(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<WHIRLPOOL> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof WHIRLPOOL
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}