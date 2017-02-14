package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetMD4;
import systems.comodal.hash.gen.DiscreteMD4;
import systems.comodal.hash.gen.LittleEndianOffsetMD4;

public interface MD4 extends Hash {

  HashFactory<MD4> FACTORY = new MD4.Factory();

  @Override
  default HashFactory<MD4> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<MD4> {

    private Factory() {
      super("MD4");
    }

    @Override
    public int getDigestLength() {
      return 16;
    }

    @Override
    public MD4 overlay(final byte[] digest) {
      return new DiscreteMD4(digest);
    }

    @Override
    public MD4 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetMD4(digest, offset);
    }

    @Override
    public MD4 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetMD4(digest, offset);
    }
  }
}