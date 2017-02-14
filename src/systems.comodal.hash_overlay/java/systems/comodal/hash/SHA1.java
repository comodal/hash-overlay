package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSHA1;
import systems.comodal.hash.gen.DiscreteSHA1;
import systems.comodal.hash.gen.LittleEndianOffsetSHA1;

public interface SHA1 extends Hash {

  HashFactory<SHA1> FACTORY = new SHA1.Factory();

  @Override
  default HashFactory<SHA1> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA1> {

    private Factory() {
      super("SHA-1");
    }

    @Override
    public int getDigestLength() {
      return 20;
    }

    @Override
    public SHA1 overlay(final byte[] digest) {
      return new DiscreteSHA1(digest);
    }

    @Override
    public SHA1 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA1(digest, offset);
    }

    @Override
    public SHA1 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA1(digest, offset);
    }
  }
}