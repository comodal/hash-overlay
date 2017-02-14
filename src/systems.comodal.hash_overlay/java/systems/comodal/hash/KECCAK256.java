package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetKECCAK256;
import systems.comodal.hash.gen.DiscreteKECCAK256;
import systems.comodal.hash.gen.LittleEndianOffsetKECCAK256;

public interface KECCAK256 extends Hash {

  HashFactory<KECCAK256> FACTORY = new KECCAK256.Factory();

  @Override
  default HashFactory<KECCAK256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<KECCAK256> {

    private Factory() {
      super("KECCAK-256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public KECCAK256 overlay(final byte[] digest) {
      return new DiscreteKECCAK256(digest);
    }

    @Override
    public KECCAK256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetKECCAK256(digest, offset);
    }

    @Override
    public KECCAK256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetKECCAK256(digest, offset);
    }
  }
}