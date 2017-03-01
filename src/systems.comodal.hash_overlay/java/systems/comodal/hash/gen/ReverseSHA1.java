package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA1;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseSHA1 extends ReverseHash implements SHA1 {

  public ReverseSHA1(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA1> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA1
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}