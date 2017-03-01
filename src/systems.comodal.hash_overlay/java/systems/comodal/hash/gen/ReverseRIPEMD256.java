package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseRIPEMD256 extends ReverseHash implements RIPEMD256 {

  public ReverseRIPEMD256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<RIPEMD256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD256
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}