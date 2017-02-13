package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSkein1024_384;
import systems.comodal.hash.base.DiscreteSkein1024_384;
import systems.comodal.hash.base.LittleEndianOffsetSkein1024_384;

public interface Skein1024_384 extends Hash {

  HashFactory<Skein1024_384> FACTORY = new Skein1024_384.Factory();

  @Override
  default HashFactory<Skein1024_384> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Skein1024_384> {

    private Factory() {
      super("Skein-1024-384");
    }

    @Override
    public int getDigestLength() {
      return 48;
    }

    @Override
    public Skein1024_384 overlay(final byte[] digest) {
      return new DiscreteSkein1024_384(digest);
    }

    @Override
    public Skein1024_384 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein1024_384(digest, offset);
    }

    @Override
    public Skein1024_384 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein1024_384(digest, offset);
    }
  }
}