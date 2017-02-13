package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSHA256;
import systems.comodal.hash.base.DiscreteSHA256;
import systems.comodal.hash.base.LittleEndianOffsetSHA256;

public interface SHA256 extends Hash {

  HashFactory<SHA256> FACTORY = new SHA256.Factory();

  @Override
  default HashFactory<SHA256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA256> {

    private Factory() {
      super("SHA-256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public SHA256 overlay(final byte[] digest) {
      return new DiscreteSHA256(digest);
    }

    @Override
    public SHA256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA256(digest, offset);
    }

    @Override
    public SHA256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA256(digest, offset);
    }
  }
}