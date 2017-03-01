package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSHA512_256;
import systems.comodal.hash.gen.SHA512_256Value;
import systems.comodal.hash.gen.ReverseSHA512_256;

public interface SHA512_256 extends Hash {

  HashFactory<SHA512_256> FACTORY = new SHA512_256.Factory();

  final class Factory extends BaseFactory<SHA512_256> {

    private Factory() {
      super("SHA-512/256");
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
      return Long.MIN_VALUE;
    }

    @Override
    public SHA512_256 overlay(final byte[] digest) {
      return new SHA512_256Value(digest);
    }

    @Override
    public SHA512_256 overlay(final byte[] digest, final int offset) {
      return new OffsetSHA512_256(digest, offset);
    }

    @Override
    public SHA512_256 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSHA512_256(digest, offset);
    }

    @Override
    public String toString() {
      return "SHA512_256.Factory - 32 byte digest";
    }
  }
}