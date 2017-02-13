package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetKECCAK224;
import systems.comodal.hash.base.DiscreteKECCAK224;
import systems.comodal.hash.base.LittleEndianOffsetKECCAK224;

public interface KECCAK224 extends Hash {

  HashFactory<KECCAK224> FACTORY = new KECCAK224.Factory();

  @Override
  default HashFactory<KECCAK224> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<KECCAK224> {

    private Factory() {
      super("KECCAK-224");
    }

    @Override
    public int getDigestLength() {
      return 28;
    }

    @Override
    public KECCAK224 overlay(final byte[] digest) {
      return new DiscreteKECCAK224(digest);
    }

    @Override
    public KECCAK224 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetKECCAK224(digest, offset);
    }

    @Override
    public KECCAK224 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetKECCAK224(digest, offset);
    }
  }
}