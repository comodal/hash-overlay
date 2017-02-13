package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetRIPEMD128;
import systems.comodal.hash.base.DiscreteRIPEMD128;
import systems.comodal.hash.base.LittleEndianOffsetRIPEMD128;

public interface RIPEMD128 extends Hash {

  HashFactory<RIPEMD128> FACTORY = new RIPEMD128.Factory();

  @Override
  default HashFactory<RIPEMD128> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<RIPEMD128> {

    private Factory() {
      super("RIPEMD128");
    }

    @Override
    public int getDigestLength() {
      return 16;
    }

    @Override
    public RIPEMD128 overlay(final byte[] digest) {
      return new DiscreteRIPEMD128(digest);
    }

    @Override
    public RIPEMD128 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetRIPEMD128(digest, offset);
    }

    @Override
    public RIPEMD128 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetRIPEMD128(digest, offset);
    }
  }
}