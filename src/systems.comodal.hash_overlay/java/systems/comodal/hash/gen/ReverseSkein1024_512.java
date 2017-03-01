package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_512;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseSkein1024_512 extends ReverseHash implements Skein1024_512 {

  public ReverseSkein1024_512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<Skein1024_512> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein1024_512
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}