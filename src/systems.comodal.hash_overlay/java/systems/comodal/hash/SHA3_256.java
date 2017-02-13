package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSHA3_256;
import systems.comodal.hash.base.DiscreteSHA3_256;
import systems.comodal.hash.base.LittleEndianOffsetSHA3_256;

public interface SHA3_256 extends Hash {

  HashFactory<SHA3_256> FACTORY = new SHA3_256.Factory();

  @Override
  default HashFactory<SHA3_256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA3_256> {

    private Factory() {
      super("SHA3-256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public SHA3_256 overlay(final byte[] digest) {
      return new DiscreteSHA3_256(digest);
    }

    @Override
    public SHA3_256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA3_256(digest, offset);
    }

    @Override
    public SHA3_256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA3_256(digest, offset);
    }
  }
}