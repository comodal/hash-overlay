package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSHA512;
import systems.comodal.hash.gen.SHA512Value;
import systems.comodal.hash.gen.LittleEndianOffsetSHA512;

public interface SHA512 extends Hash {

  HashFactory<SHA512> FACTORY = new SHA512.Factory();

  final class Factory extends BaseFactory<SHA512> {

    private Factory() {
      super("SHA-512");
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
      return 0x13;
    }

    @Override
    public SHA512 overlay(final byte[] digest) {
      return new SHA512Value(digest);
    }

    @Override
    public SHA512 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA512(digest, offset);
    }

    @Override
    public SHA512 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA512(digest, offset);
    }

    @Override
    public String toString() {
      return "SHA512.Factory - 64 byte digest";
    }
  }
}