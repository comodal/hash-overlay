package systems.comodal.hash;

import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSha256;
import systems.comodal.hash.base.DiscreteSha256;
import systems.comodal.hash.base.LittleEndianOffsetSha256;

public interface Sha256 extends Hash {

  HashFactory<Sha256> FACTORY = new Sha256.Factory();

  @Override
  default HashFactory<Sha256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Sha256> {

    private Factory() {
      super("SHA-256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public Sha256 overlay(final byte[] digest) {
      return new DiscreteSha256(digest);
    }

    @Override
    public Sha256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSha256(digest, offset);
    }

    @Override
    public Sha256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSha256(digest, offset);
    }
  }
}
