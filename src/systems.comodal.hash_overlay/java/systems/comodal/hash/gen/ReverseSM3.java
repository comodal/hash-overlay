package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SM3;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseSM3 extends ReverseHash implements SM3 {

  public ReverseSM3(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SM3> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SM3
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}