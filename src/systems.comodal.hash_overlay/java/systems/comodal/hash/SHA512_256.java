package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSHA512_256;
import systems.comodal.hash.gen.DiscreteSHA512_256;
import systems.comodal.hash.gen.LittleEndianOffsetSHA512_256;

public interface SHA512_256 extends Hash {

  HashFactory<SHA512_256> FACTORY = new SHA512_256.Factory();

  @Override
  default HashFactory<SHA512_256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA512_256> {

    private Factory() {
      super("SHA-512/256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public SHA512_256 overlay(final byte[] digest) {
      return new DiscreteSHA512_256(digest);
    }

    @Override
    public SHA512_256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA512_256(digest, offset);
    }

    @Override
    public SHA512_256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA512_256(digest, offset);
    }
  }
}