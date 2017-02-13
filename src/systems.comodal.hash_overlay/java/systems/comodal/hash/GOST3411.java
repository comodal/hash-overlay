package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetGOST3411;
import systems.comodal.hash.base.DiscreteGOST3411;
import systems.comodal.hash.base.LittleEndianOffsetGOST3411;

public interface GOST3411 extends Hash {

  HashFactory<GOST3411> FACTORY = new GOST3411.Factory();

  @Override
  default HashFactory<GOST3411> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<GOST3411> {

    private Factory() {
      super("GOST3411");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public GOST3411 overlay(final byte[] digest) {
      return new DiscreteGOST3411(digest);
    }

    @Override
    public GOST3411 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetGOST3411(digest, offset);
    }

    @Override
    public GOST3411 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetGOST3411(digest, offset);
    }
  }
}