package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSkein512_256;
import systems.comodal.hash.base.DiscreteSkein512_256;
import systems.comodal.hash.base.LittleEndianOffsetSkein512_256;

public interface Skein512_256 extends Hash {

  HashFactory<Skein512_256> FACTORY = new Skein512_256.Factory();

  @Override
  default HashFactory<Skein512_256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Skein512_256> {

    private Factory() {
      super("Skein-512-256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public Skein512_256 overlay(final byte[] digest) {
      return new DiscreteSkein512_256(digest);
    }

    @Override
    public Skein512_256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein512_256(digest, offset);
    }

    @Override
    public Skein512_256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein512_256(digest, offset);
    }
  }
}