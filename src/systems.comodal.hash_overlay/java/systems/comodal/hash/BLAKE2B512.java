package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetBLAKE2B512;
import systems.comodal.hash.gen.DiscreteBLAKE2B512;
import systems.comodal.hash.gen.LittleEndianOffsetBLAKE2B512;

public interface BLAKE2B512 extends Hash {

  HashFactory<BLAKE2B512> FACTORY = new BLAKE2B512.Factory();

  final class Factory extends BaseFactory<BLAKE2B512> {

    private Factory() {
      super("BLAKE2B-512");
    }

    @Override
    public int getDigestLength() {
      return 64;
    }

    @Override
    public BLAKE2B512 overlay(final byte[] digest) {
      return new DiscreteBLAKE2B512(digest);
    }

    @Override
    public BLAKE2B512 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetBLAKE2B512(digest, offset);
    }

    @Override
    public BLAKE2B512 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetBLAKE2B512(digest, offset);
    }

    @Override
    public String toString() {
      return "BLAKE2B512.Factory - 64 byte digest";
    }
  }
}