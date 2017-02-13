package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSkein256_224;
import systems.comodal.hash.base.DiscreteSkein256_224;
import systems.comodal.hash.base.LittleEndianOffsetSkein256_224;

public interface Skein256_224 extends Hash {

  HashFactory<Skein256_224> FACTORY = new Skein256_224.Factory();

  @Override
  default HashFactory<Skein256_224> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Skein256_224> {

    private Factory() {
      super("Skein-256-224");
    }

    @Override
    public int getDigestLength() {
      return 28;
    }

    @Override
    public Skein256_224 overlay(final byte[] digest) {
      return new DiscreteSkein256_224(digest);
    }

    @Override
    public Skein256_224 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein256_224(digest, offset);
    }

    @Override
    public Skein256_224 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein256_224(digest, offset);
    }
  }
}