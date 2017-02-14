package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetBLAKE2B160;
import systems.comodal.hash.gen.DiscreteBLAKE2B160;
import systems.comodal.hash.gen.LittleEndianOffsetBLAKE2B160;

public interface BLAKE2B160 extends Hash {

  HashFactory<BLAKE2B160> FACTORY = new BLAKE2B160.Factory();

  @Override
  default HashFactory<BLAKE2B160> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<BLAKE2B160> {

    private Factory() {
      super("BLAKE2B-160");
    }

    @Override
    public int getDigestLength() {
      return 20;
    }

    @Override
    public BLAKE2B160 overlay(final byte[] digest) {
      return new DiscreteBLAKE2B160(digest);
    }

    @Override
    public BLAKE2B160 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetBLAKE2B160(digest, offset);
    }

    @Override
    public BLAKE2B160 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetBLAKE2B160(digest, offset);
    }
  }
}