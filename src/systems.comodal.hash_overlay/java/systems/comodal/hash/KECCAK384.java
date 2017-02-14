package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetKECCAK384;
import systems.comodal.hash.gen.DiscreteKECCAK384;
import systems.comodal.hash.gen.LittleEndianOffsetKECCAK384;

public interface KECCAK384 extends Hash {

  HashFactory<KECCAK384> FACTORY = new KECCAK384.Factory();

  @Override
  default HashFactory<KECCAK384> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<KECCAK384> {

    private Factory() {
      super("KECCAK-384");
    }

    @Override
    public int getDigestLength() {
      return 48;
    }

    @Override
    public KECCAK384 overlay(final byte[] digest) {
      return new DiscreteKECCAK384(digest);
    }

    @Override
    public KECCAK384 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetKECCAK384(digest, offset);
    }

    @Override
    public KECCAK384 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetKECCAK384(digest, offset);
    }
  }
}