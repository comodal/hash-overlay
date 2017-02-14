package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetRIPEMD160;
import systems.comodal.hash.gen.DiscreteRIPEMD160;
import systems.comodal.hash.gen.LittleEndianOffsetRIPEMD160;

public interface RIPEMD160 extends Hash {

  HashFactory<RIPEMD160> FACTORY = new RIPEMD160.Factory();

  @Override
  default HashFactory<RIPEMD160> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<RIPEMD160> {

    private Factory() {
      super("RIPEMD160");
    }

    @Override
    public int getDigestLength() {
      return 20;
    }

    @Override
    public RIPEMD160 overlay(final byte[] digest) {
      return new DiscreteRIPEMD160(digest);
    }

    @Override
    public RIPEMD160 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetRIPEMD160(digest, offset);
    }

    @Override
    public RIPEMD160 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetRIPEMD160(digest, offset);
    }
  }
}