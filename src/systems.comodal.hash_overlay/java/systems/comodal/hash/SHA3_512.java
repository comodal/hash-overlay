package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSHA3_512;
import systems.comodal.hash.gen.DiscreteSHA3_512;
import systems.comodal.hash.gen.LittleEndianOffsetSHA3_512;

public interface SHA3_512 extends Hash {

  HashFactory<SHA3_512> FACTORY = new SHA3_512.Factory();

  @Override
  default HashFactory<SHA3_512> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA3_512> {

    private Factory() {
      super("SHA3-512");
    }

    @Override
    public int getDigestLength() {
      return 64;
    }

    @Override
    public SHA3_512 overlay(final byte[] digest) {
      return new DiscreteSHA3_512(digest);
    }

    @Override
    public SHA3_512 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA3_512(digest, offset);
    }

    @Override
    public SHA3_512 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA3_512(digest, offset);
    }
  }
}