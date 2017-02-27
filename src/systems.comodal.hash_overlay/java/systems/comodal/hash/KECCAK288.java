package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetKECCAK288;
import systems.comodal.hash.gen.KECCAK288Value;
import systems.comodal.hash.gen.LittleEndianOffsetKECCAK288;

public interface KECCAK288 extends Hash {

  HashFactory<KECCAK288> FACTORY = new KECCAK288.Factory();

  final class Factory extends BaseFactory<KECCAK288> {

    private Factory() {
      super("KECCAK-288");
    }

    @Override
    public int getDigestLength() {
      return 36;
    }

    @Override
    public int getOffsetLength() {
      return 35;
    }

    @Override
    public long getMultiHashFnCode() {
      return Long.MIN_VALUE;
    }

    @Override
    public KECCAK288 overlay(final byte[] digest) {
      return new KECCAK288Value(digest);
    }

    @Override
    public KECCAK288 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetKECCAK288(digest, offset);
    }

    @Override
    public KECCAK288 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetKECCAK288(digest, offset);
    }

    @Override
    public String toString() {
      return "KECCAK288.Factory - 36 byte digest";
    }
  }
}