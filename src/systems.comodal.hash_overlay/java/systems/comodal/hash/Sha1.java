package systems.comodal.hash;

import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSha1;
import systems.comodal.hash.base.DiscreteSha1;
import systems.comodal.hash.base.LittleEndianOffsetSha1;

public interface Sha1 extends Hash {

  HashFactory<Sha1> FACTORY = new Sha1.Factory();

  @Override
  default HashFactory<Sha1> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Sha1> {

    private Factory() {
      super("SHA-1");
    }

    @Override
    public int getDigestLength() {
      return 20;
    }

    @Override
    public Sha1 overlay(final byte[] digest) {
      return new DiscreteSha1(digest);
    }

    @Override
    public Sha1 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSha1(digest, offset);
    }

    @Override
    public Sha1 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSha1(digest, offset);
    }
  }
}
