package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_160;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseSkein256_160 extends ReverseHash implements Skein256_160 {

  public ReverseSkein256_160(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<Skein256_160> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein256_160
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}