package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseSkein256_256 extends ReverseHash implements Skein256_256 {

  public ReverseSkein256_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<Skein256_256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein256_256
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}