package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSkein256_256;
import systems.comodal.hash.base.DiscreteSkein256_256;
import systems.comodal.hash.base.LittleEndianOffsetSkein256_256;

public interface Skein256_256 extends Hash {

  HashFactory<Skein256_256> FACTORY = new Skein256_256.Factory();

  @Override
  default HashFactory<Skein256_256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Skein256_256> {

    private Factory() {
      super("Skein-256-256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public Skein256_256 overlay(final byte[] digest) {
      return new DiscreteSkein256_256(digest);
    }

    @Override
    public Skein256_256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein256_256(digest, offset);
    }

    @Override
    public Skein256_256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein256_256(digest, offset);
    }
  }
}