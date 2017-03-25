package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD4;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseMD4 extends ReverseHash implements MD4 {

  public ReverseMD4(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<MD4> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD4
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}