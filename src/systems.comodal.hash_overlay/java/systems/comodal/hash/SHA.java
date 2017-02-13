package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetSHA;
import systems.comodal.hash.base.DiscreteSHA;
import systems.comodal.hash.base.LittleEndianOffsetSHA;

public interface SHA extends Hash {

  HashFactory<SHA> FACTORY = new SHA.Factory();

  @Override
  default HashFactory<SHA> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA> {

    private Factory() {
      super("SHA");
    }

    @Override
    public int getDigestLength() {
      return 20;
    }

    @Override
    public SHA overlay(final byte[] digest) {
      return new DiscreteSHA(digest);
    }

    @Override
    public SHA overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA(digest, offset);
    }

    @Override
    public SHA reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA(digest, offset);
    }
  }
}