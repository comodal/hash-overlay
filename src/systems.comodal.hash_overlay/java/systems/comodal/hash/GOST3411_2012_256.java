package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetGOST3411_2012_256;
import systems.comodal.hash.base.DiscreteGOST3411_2012_256;
import systems.comodal.hash.base.LittleEndianOffsetGOST3411_2012_256;

public interface GOST3411_2012_256 extends Hash {

  HashFactory<GOST3411_2012_256> FACTORY = new GOST3411_2012_256.Factory();

  @Override
  default HashFactory<GOST3411_2012_256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<GOST3411_2012_256> {

    private Factory() {
      super("GOST3411-2012-256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public GOST3411_2012_256 overlay(final byte[] digest) {
      return new DiscreteGOST3411_2012_256(digest);
    }

    @Override
    public GOST3411_2012_256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetGOST3411_2012_256(digest, offset);
    }

    @Override
    public GOST3411_2012_256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetGOST3411_2012_256(digest, offset);
    }
  }
}