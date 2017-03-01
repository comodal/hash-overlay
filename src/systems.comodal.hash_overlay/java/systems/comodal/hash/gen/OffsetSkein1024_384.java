package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_384;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetSkein1024_384 extends OffsetHash implements Skein1024_384 {

  public OffsetSkein1024_384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<Skein1024_384> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein1024_384
        && ((Hash) other).digestEquals(data, offset);
  }
}