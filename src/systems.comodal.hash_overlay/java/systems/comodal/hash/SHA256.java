package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSHA256;
import systems.comodal.hash.gen.SHA256Value;
import systems.comodal.hash.gen.LittleEndianOffsetSHA256;

public interface SHA256 extends Hash {

  HashFactory<SHA256> FACTORY = new SHA256.Factory();

  final class Factory extends BaseFactory<SHA256> {

    private Factory() {
      super("SHA-256");
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
    public SHA256 overlay(final byte[] digest) {
      return new SHA256Value(digest);
    }

    @Override
    public SHA256 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA256(digest, offset);
    }

    @Override
    public SHA256 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA256(digest, offset);
    }

    @Override
    public String toString() {
      return "SHA256.Factory - 32 byte digest";
    }
  }
}