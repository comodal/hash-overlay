package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_384;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseSkein1024_384 extends ReverseHash implements Skein1024_384 {

  public ReverseSkein1024_384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<Skein1024_384> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein1024_384
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}