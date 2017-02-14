package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSkein512_224;
import systems.comodal.hash.gen.DiscreteSkein512_224;
import systems.comodal.hash.gen.LittleEndianOffsetSkein512_224;

public interface Skein512_224 extends Hash {

  HashFactory<Skein512_224> FACTORY = new Skein512_224.Factory();

  @Override
  default HashFactory<Skein512_224> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Skein512_224> {

    private Factory() {
      super("Skein-512-224");
    }

    @Override
    public int getDigestLength() {
      return 28;
    }

    @Override
    public Skein512_224 overlay(final byte[] digest) {
      return new DiscreteSkein512_224(digest);
    }

    @Override
    public Skein512_224 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein512_224(digest, offset);
    }

    @Override
    public Skein512_224 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein512_224(digest, offset);
    }
  }
}