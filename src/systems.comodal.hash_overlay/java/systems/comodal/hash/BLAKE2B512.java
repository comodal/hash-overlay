package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetBLAKE2B512;
import systems.comodal.hash.gen.BLAKE2B512Value;
import systems.comodal.hash.gen.ReverseBLAKE2B512;

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
    public int getOffsetLength() {
      return 63;
    }

    @Override
    public long getMultiHashFnCode() {
      return 0xB240;
    }

    @Override
    public BLAKE2B512 overlay(final byte[] digest) {
      return new BLAKE2B512Value(digest);
    }

    @Override
    public BLAKE2B512 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetBLAKE2B512(digest, offset);
    }

    @Override
    public BLAKE2B512 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseBLAKE2B512(digest, offset);
    }

    @Override
    public String toString() {
      return "BLAKE2B512.Factory - 64 byte digest";
    }
  }
}