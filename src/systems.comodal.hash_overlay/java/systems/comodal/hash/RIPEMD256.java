package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetRIPEMD256;
import systems.comodal.hash.gen.DiscreteRIPEMD256;
import systems.comodal.hash.gen.LittleEndianOffsetRIPEMD256;

public interface RIPEMD256 extends Hash {

  HashFactory<RIPEMD256> FACTORY = new RIPEMD256.Factory();

  @Override
  default HashFactory<RIPEMD256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<RIPEMD256> {

    private Factory() {
      super("RIPEMD256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public RIPEMD256 overlay(final byte[] digest) {
      return new DiscreteRIPEMD256(digest);
    }

    @Override
    public RIPEMD256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetRIPEMD256(digest, offset);
    }

    @Override
    public RIPEMD256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetRIPEMD256(digest, offset);
    }
  }
}