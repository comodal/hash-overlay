package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BigEndianOffsetHash;

public final class BigEndianOffsetSkein512_256 extends BigEndianOffsetHash implements Skein512_256 {

  public BigEndianOffsetSkein512_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<Skein512_256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein512_256
        && ((Hash) other).digestEquals(data, offset);
  }
}