package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSHA512;
import systems.comodal.hash.base.DiscreteSHA512;
import systems.comodal.hash.base.LittleEndianOffsetSHA512;

public interface SHA512 extends Hash {

  HashFactory<SHA512> FACTORY = new SHA512.Factory();

  @Override
  default HashFactory<SHA512> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA512> {

    private Factory() {
      super("SHA-512");
    }

    @Override
    public int getDigestLength() {
      return 64;
    }

    @Override
    public SHA512 overlay(final byte[] digest) {
      return new DiscreteSHA512(digest);
    }

    @Override
    public SHA512 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA512(digest, offset);
    }

    @Override
    public SHA512 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA512(digest, offset);
    }
  }
}