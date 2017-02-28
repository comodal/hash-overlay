package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_224;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetSkein256_224 extends LittleEndianOffsetHash implements Skein256_224 {

  public LittleEndianOffsetSkein256_224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<Skein256_224> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein256_224
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}