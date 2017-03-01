package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_512;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetSkein1024_512 extends OffsetHash implements Skein1024_512 {

  public OffsetSkein1024_512(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<Skein1024_512> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein1024_512
        && ((Hash) other).digestEquals(data, offset);
  }
}