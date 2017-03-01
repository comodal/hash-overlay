package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512_256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseSHA512_256 extends ReverseHash implements SHA512_256 {

  public ReverseSHA512_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA512_256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA512_256
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}