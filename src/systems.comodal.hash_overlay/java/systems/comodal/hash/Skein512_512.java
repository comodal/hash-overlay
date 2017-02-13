package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSkein512_512;
import systems.comodal.hash.base.DiscreteSkein512_512;
import systems.comodal.hash.base.LittleEndianOffsetSkein512_512;

public interface Skein512_512 extends Hash {

  HashFactory<Skein512_512> FACTORY = new Skein512_512.Factory();

  @Override
  default HashFactory<Skein512_512> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Skein512_512> {

    private Factory() {
      super("Skein-512-512");
    }

    @Override
    public int getDigestLength() {
      return 64;
    }

    @Override
    public Skein512_512 overlay(final byte[] digest) {
      return new DiscreteSkein512_512(digest);
    }

    @Override
    public Skein512_512 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein512_512(digest, offset);
    }

    @Override
    public Skein512_512 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein512_512(digest, offset);
    }
  }
}