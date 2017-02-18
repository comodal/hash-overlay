package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetKECCAK224;
import systems.comodal.hash.gen.KECCAK224Value;
import systems.comodal.hash.gen.LittleEndianOffsetKECCAK224;

public interface KECCAK224 extends Hash {

  HashFactory<KECCAK224> FACTORY = new KECCAK224.Factory();

  final class Factory extends BaseFactory<KECCAK224> {

    private Factory() {
      super("KECCAK-224");
    }

    @Override
    public int getDigestLength() {
      return 28;
    }

    @Override
    public int getOffsetLength() {
      return 27;
    }

    @Override
    public KECCAK224 overlay(final byte[] digest) {
      return new KECCAK224Value(digest);
    }

    @Override
    public KECCAK224 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetKECCAK224(digest, offset);
    }

    @Override
    public KECCAK224 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetKECCAK224(digest, offset);
    }

    @Override
    public String toString() {
      return "KECCAK224.Factory - 28 byte digest";
    }
  }
}