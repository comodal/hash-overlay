package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512_224;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseSHA512_224 extends ReverseHash implements SHA512_224 {

  public ReverseSHA512_224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA512_224> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA512_224
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}