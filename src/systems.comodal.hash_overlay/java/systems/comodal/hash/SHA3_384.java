package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSHA3_384;
import systems.comodal.hash.base.DiscreteSHA3_384;
import systems.comodal.hash.base.LittleEndianOffsetSHA3_384;

public interface SHA3_384 extends Hash {

  HashFactory<SHA3_384> FACTORY = new SHA3_384.Factory();

  @Override
  default HashFactory<SHA3_384> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA3_384> {

    private Factory() {
      super("SHA3-384");
    }

    @Override
    public int getDigestLength() {
      return 48;
    }

    @Override
    public SHA3_384 overlay(final byte[] digest) {
      return new DiscreteSHA3_384(digest);
    }

    @Override
    public SHA3_384 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA3_384(digest, offset);
    }

    @Override
    public SHA3_384 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA3_384(digest, offset);
    }
  }
}