package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSHA224;
import systems.comodal.hash.base.DiscreteSHA224;
import systems.comodal.hash.base.LittleEndianOffsetSHA224;

public interface SHA224 extends Hash {

  HashFactory<SHA224> FACTORY = new SHA224.Factory();

  @Override
  default HashFactory<SHA224> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA224> {

    private Factory() {
      super("SHA-224");
    }

    @Override
    public int getDigestLength() {
      return 28;
    }

    @Override
    public SHA224 overlay(final byte[] digest) {
      return new DiscreteSHA224(digest);
    }

    @Override
    public SHA224 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA224(digest, offset);
    }

    @Override
    public SHA224 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA224(digest, offset);
    }
  }
}