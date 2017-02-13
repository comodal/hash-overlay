package systems.comodal.hash;

import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSha3_256;
import systems.comodal.hash.base.DiscreteSha3_256;
import systems.comodal.hash.base.LittleEndianOffsetSha3_256;

public interface Sha3_256 extends Hash {

  HashFactory<Sha3_256> FACTORY = new Factory();

  @Override
  default HashFactory<Sha3_256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Sha3_256> {

    private Factory() {
      super("SHA3-256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public Sha3_256 overlay(final byte[] digest) {
      return new DiscreteSha3_256(digest);
    }

    @Override
    public Sha3_256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSha3_256(digest, offset);
    }

    @Override
    public Sha3_256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSha3_256(digest, offset);
    }
  }
}