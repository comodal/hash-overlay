package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetBLAKE2B256;
import systems.comodal.hash.base.DiscreteBLAKE2B256;
import systems.comodal.hash.base.LittleEndianOffsetBLAKE2B256;

public interface BLAKE2B256 extends Hash {

  HashFactory<BLAKE2B256> FACTORY = new BLAKE2B256.Factory();

  @Override
  default HashFactory<BLAKE2B256> getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<BLAKE2B256> {

    private Factory() {
      super("BLAKE2B-256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public BLAKE2B256 overlay(final byte[] digest) {
      return new DiscreteBLAKE2B256(digest);
    }

    @Override
    public BLAKE2B256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetBLAKE2B256(digest, offset);
    }

    @Override
    public BLAKE2B256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetBLAKE2B256(digest, offset);
    }
  }
}