package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSkein1024_1024;
import systems.comodal.hash.gen.DiscreteSkein1024_1024;
import systems.comodal.hash.gen.LittleEndianOffsetSkein1024_1024;

public interface Skein1024_1024 extends Hash {

  HashFactory<Skein1024_1024> FACTORY = new Skein1024_1024.Factory();

  @Override
  default HashFactory<Skein1024_1024> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Skein1024_1024> {

    private Factory() {
      super("Skein-1024-1024");
    }

    @Override
    public int getDigestLength() {
      return 128;
    }

    @Override
    public Skein1024_1024 overlay(final byte[] digest) {
      return new DiscreteSkein1024_1024(digest);
    }

    @Override
    public Skein1024_1024 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein1024_1024(digest, offset);
    }

    @Override
    public Skein1024_1024 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein1024_1024(digest, offset);
    }
  }
}