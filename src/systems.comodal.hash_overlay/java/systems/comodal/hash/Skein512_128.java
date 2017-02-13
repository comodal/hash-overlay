package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSkein512_128;
import systems.comodal.hash.base.DiscreteSkein512_128;
import systems.comodal.hash.base.LittleEndianOffsetSkein512_128;

public interface Skein512_128 extends Hash {

  HashFactory<Skein512_128> FACTORY = new Skein512_128.Factory();

  @Override
  default HashFactory<Skein512_128> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Skein512_128> {

    private Factory() {
      super("Skein-512-128");
    }

    @Override
    public int getDigestLength() {
      return 16;
    }

    @Override
    public Skein512_128 overlay(final byte[] digest) {
      return new DiscreteSkein512_128(digest);
    }

    @Override
    public Skein512_128 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein512_128(digest, offset);
    }

    @Override
    public Skein512_128 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein512_128(digest, offset);
    }
  }
}