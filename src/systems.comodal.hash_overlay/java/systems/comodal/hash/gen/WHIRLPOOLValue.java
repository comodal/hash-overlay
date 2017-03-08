package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.WHIRLPOOL;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class WHIRLPOOLValue extends HashValue implements WHIRLPOOL {

  public WHIRLPOOLValue(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<WHIRLPOOL> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof WHIRLPOOL
        && ((Hash) other).digestEquals(data);
  }
}