package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseSHA512 extends ReverseHash implements SHA512 {

  public ReverseSHA512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA512> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA512
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}