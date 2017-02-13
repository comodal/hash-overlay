package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetTIGER;
import systems.comodal.hash.base.DiscreteTIGER;
import systems.comodal.hash.base.LittleEndianOffsetTIGER;

public interface TIGER extends Hash {

  HashFactory<TIGER> FACTORY = new TIGER.Factory();

  @Override
  default HashFactory<TIGER> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<TIGER> {

    private Factory() {
      super("TIGER");
    }

    @Override
    public int getDigestLength() {
      return 24;
    }

    @Override
    public TIGER overlay(final byte[] digest) {
      return new DiscreteTIGER(digest);
    }

    @Override
    public TIGER overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetTIGER(digest, offset);
    }

    @Override
    public TIGER reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetTIGER(digest, offset);
    }
  }
}