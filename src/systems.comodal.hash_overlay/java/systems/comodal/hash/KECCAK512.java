package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetKECCAK512;
import systems.comodal.hash.gen.KECCAK512Value;
import systems.comodal.hash.gen.ReverseKECCAK512;

public interface KECCAK512 extends Hash {

  HashFactory<KECCAK512> FACTORY = new KECCAK512.Factory();

  final class Factory extends BaseFactory<KECCAK512> {

    private Factory() {
      super("KECCAK-512");
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
      return 0x1D;
    }

    @Override
    public KECCAK512 overlay(final byte[] digest) {
      return new KECCAK512Value(digest);
    }

    @Override
    public KECCAK512 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetKECCAK512(digest, offset);
    }

    @Override
    public KECCAK512 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseKECCAK512(digest, offset);
    }

    @Override
    public String toString() {
      return "KECCAK512.Factory - 64 byte digest";
    }
  }
}