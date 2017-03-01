package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_128;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetSkein512_128 extends OffsetHash implements Skein512_128 {

  public OffsetSkein512_128(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<Skein512_128> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein512_128
        && ((Hash) other).digestEquals(data, offset);
  }
}