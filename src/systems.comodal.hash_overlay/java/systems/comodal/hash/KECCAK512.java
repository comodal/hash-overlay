package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetKECCAK512;
import systems.comodal.hash.gen.DiscreteKECCAK512;
import systems.comodal.hash.gen.LittleEndianOffsetKECCAK512;

public interface KECCAK512 extends Hash {

  HashFactory<KECCAK512> FACTORY = new KECCAK512.Factory();

  @Override
  default HashFactory<KECCAK512> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<KECCAK512> {

    private Factory() {
      super("KECCAK-512");
    }

    @Override
    public int getDigestLength() {
      return 64;
    }

    @Override
    public KECCAK512 overlay(final byte[] digest) {
      return new DiscreteKECCAK512(digest);
    }

    @Override
    public KECCAK512 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetKECCAK512(digest, offset);
    }

    @Override
    public KECCAK512 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetKECCAK512(digest, offset);
    }
  }
}