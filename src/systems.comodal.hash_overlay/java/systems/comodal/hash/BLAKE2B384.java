package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetBLAKE2B384;
import systems.comodal.hash.gen.BLAKE2B384Value;
import systems.comodal.hash.gen.LittleEndianOffsetBLAKE2B384;

public interface BLAKE2B384 extends Hash {

  HashFactory<BLAKE2B384> FACTORY = new BLAKE2B384.Factory();

  final class Factory extends BaseFactory<BLAKE2B384> {

    private Factory() {
      super("BLAKE2B-384");
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
    public BLAKE2B384 overlay(final byte[] digest) {
      return new BLAKE2B384Value(digest);
    }

    @Override
    public BLAKE2B384 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetBLAKE2B384(digest, offset);
    }

    @Override
    public BLAKE2B384 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetBLAKE2B384(digest, offset);
    }

    @Override
    public String toString() {
      return "BLAKE2B384.Factory - 48 byte digest";
    }
  }
}