package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetWHIRLPOOL;
import systems.comodal.hash.gen.DiscreteWHIRLPOOL;
import systems.comodal.hash.gen.LittleEndianOffsetWHIRLPOOL;

public interface WHIRLPOOL extends Hash {

  HashFactory<WHIRLPOOL> FACTORY = new WHIRLPOOL.Factory();

  @Override
  default HashFactory<WHIRLPOOL> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<WHIRLPOOL> {

    private Factory() {
      super("WHIRLPOOL");
    }

    @Override
    public int getDigestLength() {
      return 64;
    }

    @Override
    public WHIRLPOOL overlay(final byte[] digest) {
      return new DiscreteWHIRLPOOL(digest);
    }

    @Override
    public WHIRLPOOL overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetWHIRLPOOL(digest, offset);
    }

    @Override
    public WHIRLPOOL reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetWHIRLPOOL(digest, offset);
    }
  }
}