package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK384;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseKECCAK384 extends ReverseHash implements KECCAK384 {

  public ReverseKECCAK384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<KECCAK384> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK384
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}