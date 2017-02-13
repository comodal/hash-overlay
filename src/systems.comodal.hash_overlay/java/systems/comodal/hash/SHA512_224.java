package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSHA512_224;
import systems.comodal.hash.base.DiscreteSHA512_224;
import systems.comodal.hash.base.LittleEndianOffsetSHA512_224;

public interface SHA512_224 extends Hash {

  HashFactory<SHA512_224> FACTORY = new SHA512_224.Factory();

  @Override
  default HashFactory<SHA512_224> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA512_224> {

    private Factory() {
      super("SHA-512/224");
    }

    @Override
    public int getDigestLength() {
      return 28;
    }

    @Override
    public SHA512_224 overlay(final byte[] digest) {
      return new DiscreteSHA512_224(digest);
    }

    @Override
    public SHA512_224 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA512_224(digest, offset);
    }

    @Override
    public SHA512_224 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA512_224(digest, offset);
    }
  }
}