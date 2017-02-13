package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSkein1024_512;
import systems.comodal.hash.base.DiscreteSkein1024_512;
import systems.comodal.hash.base.LittleEndianOffsetSkein1024_512;

public interface Skein1024_512 extends Hash {

  HashFactory<Skein1024_512> FACTORY = new Skein1024_512.Factory();

  @Override
  default HashFactory<Skein1024_512> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Skein1024_512> {

    private Factory() {
      super("Skein-1024-512");
    }

    @Override
    public int getDigestLength() {
      return 64;
    }

    @Override
    public Skein1024_512 overlay(final byte[] digest) {
      return new DiscreteSkein1024_512(digest);
    }

    @Override
    public Skein1024_512 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein1024_512(digest, offset);
    }

    @Override
    public Skein1024_512 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein1024_512(digest, offset);
    }
  }
}