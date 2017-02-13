package systems.comodal.hash;

import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetMd5;
import systems.comodal.hash.base.DiscreteMd5;
import systems.comodal.hash.base.LittleEndianOffsetMd5;

public interface Md5 extends Hash {

  HashFactory<Md5> FACTORY = new Md5.Factory();

  @Override
  default HashFactory<Md5> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<Md5> {

    private Factory() {
      super("MD5");
    }

    @Override
    public int getDigestLength() {
      return 16;
    }

    @Override
    public Md5 overlay(final byte[] digest) {
      return new DiscreteMd5(digest);
    }

    @Override
    public Md5 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetMd5(digest, offset);
    }

    @Override
    public Md5 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetMd5(digest, offset);
    }
  }
}
