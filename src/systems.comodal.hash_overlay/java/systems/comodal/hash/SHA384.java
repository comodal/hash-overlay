package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSHA384;
import systems.comodal.hash.gen.DiscreteSHA384;
import systems.comodal.hash.gen.LittleEndianOffsetSHA384;

public interface SHA384 extends Hash {

  HashFactory<SHA384> FACTORY = new SHA384.Factory();

  @Override
  default HashFactory<SHA384> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<SHA384> {

    private Factory() {
      super("SHA-384");
    }

    @Override
    public int getDigestLength() {
      return 48;
    }

    @Override
    public SHA384 overlay(final byte[] digest) {
      return new DiscreteSHA384(digest);
    }

    @Override
    public SHA384 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA384(digest, offset);
    }

    @Override
    public SHA384 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA384(digest, offset);
    }
  }
}