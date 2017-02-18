package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetKECCAK384;
import systems.comodal.hash.gen.KECCAK384Value;
import systems.comodal.hash.gen.LittleEndianOffsetKECCAK384;

public interface KECCAK384 extends Hash {

  HashFactory<KECCAK384> FACTORY = new KECCAK384.Factory();

  final class Factory extends BaseFactory<KECCAK384> {

    private Factory() {
      super("KECCAK-384");
    }

    @Override
    public int getDigestLength() {
      return 48;
    }

    @Override
    public int getOffsetLength() {
      return 47;
    }

    @Override
    public KECCAK384 overlay(final byte[] digest) {
      return new KECCAK384Value(digest);
    }

    @Override
    public KECCAK384 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetKECCAK384(digest, offset);
    }

    @Override
    public KECCAK384 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetKECCAK384(digest, offset);
    }

    @Override
    public String toString() {
      return "KECCAK384.Factory - 48 byte digest";
    }
  }
}