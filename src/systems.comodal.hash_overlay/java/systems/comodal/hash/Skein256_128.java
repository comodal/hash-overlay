package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSkein256_128;
import systems.comodal.hash.gen.DiscreteSkein256_128;
import systems.comodal.hash.gen.LittleEndianOffsetSkein256_128;

public interface Skein256_128 extends Hash {

  HashFactory<Skein256_128> FACTORY = new Skein256_128.Factory();

  @Override
  default HashFactory<Skein256_128> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Skein256_128> {

    private Factory() {
      super("Skein-256-128");
    }

    @Override
    public int getDigestLength() {
      return 16;
    }

    @Override
    public Skein256_128 overlay(final byte[] digest) {
      return new DiscreteSkein256_128(digest);
    }

    @Override
    public Skein256_128 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein256_128(digest, offset);
    }

    @Override
    public Skein256_128 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein256_128(digest, offset);
    }
  }
}