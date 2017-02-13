package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSHA3_224;
import systems.comodal.hash.base.DiscreteSHA3_224;
import systems.comodal.hash.base.LittleEndianOffsetSHA3_224;

public interface SHA3_224 extends Hash {

  HashFactory<SHA3_224> FACTORY = new SHA3_224.Factory();

  @Override
  default HashFactory<SHA3_224> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA3_224> {

    private Factory() {
      super("SHA3-224");
    }

    @Override
    public int getDigestLength() {
      return 28;
    }

    @Override
    public SHA3_224 overlay(final byte[] digest) {
      return new DiscreteSHA3_224(digest);
    }

    @Override
    public SHA3_224 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA3_224(digest, offset);
    }

    @Override
    public SHA3_224 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA3_224(digest, offset);
    }
  }
}