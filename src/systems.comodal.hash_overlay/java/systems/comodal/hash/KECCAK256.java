package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetKECCAK256;
import systems.comodal.hash.gen.KECCAK256Value;
import systems.comodal.hash.gen.ReverseKECCAK256;

public interface KECCAK256 extends Hash {

  HashFactory<KECCAK256> FACTORY = new KECCAK256.Factory();

  final class Factory extends BaseFactory<KECCAK256> {

    private Factory() {
      super("KECCAK-256");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public int getOffsetLength() {
      return 31;
    }

    @Override
    public long getMultiHashFnCode() {
      return 0x1B;
    }

    @Override
    public KECCAK256 overlay(final byte[] digest) {
      return new KECCAK256Value(digest);
    }

    @Override
    public KECCAK256 overlay(final byte[] digest, final int offset) {
      return new OffsetKECCAK256(digest, offset);
    }

    @Override
    public KECCAK256 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseKECCAK256(digest, offset);
    }

    @Override
    public String toString() {
      return "KECCAK256.Factory - 32 byte digest";
    }
  }
}