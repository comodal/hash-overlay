package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetBLAKE2B256;
import systems.comodal.hash.gen.DiscreteBLAKE2B256;
import systems.comodal.hash.gen.LittleEndianOffsetBLAKE2B256;

public interface BLAKE2B256 extends Hash {

  HashFactory<BLAKE2B256> FACTORY = new BLAKE2B256.Factory();

  final class Factory extends BaseFactory<BLAKE2B256> {

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

    @Override
    public String toString() {
      return "BLAKE2B256.Factory - 32 byte digest";
    }
  }
}