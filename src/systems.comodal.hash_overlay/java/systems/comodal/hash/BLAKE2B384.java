package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetBLAKE2B384;
import systems.comodal.hash.base.DiscreteBLAKE2B384;
import systems.comodal.hash.base.LittleEndianOffsetBLAKE2B384;

public interface BLAKE2B384 extends Hash {

  HashFactory<BLAKE2B384> FACTORY = new BLAKE2B384.Factory();

  @Override
  default HashFactory<BLAKE2B384> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<BLAKE2B384> {

    private Factory() {
      super("BLAKE2B-384");
    }

    @Override
    public int getDigestLength() {
      return 48;
    }

    @Override
    public BLAKE2B384 overlay(final byte[] digest) {
      return new DiscreteBLAKE2B384(digest);
    }

    @Override
    public BLAKE2B384 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetBLAKE2B384(digest, offset);
    }

    @Override
    public BLAKE2B384 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetBLAKE2B384(digest, offset);
    }
  }
}