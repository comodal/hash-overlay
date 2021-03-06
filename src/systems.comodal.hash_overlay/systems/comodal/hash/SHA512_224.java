package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSHA512_224;
import systems.comodal.hash.gen.SHA512_224Value;
import systems.comodal.hash.gen.ReverseSHA512_224;

public interface SHA512_224 extends Hash {

  HashFactory<SHA512_224> FACTORY = new SHA512_224.Factory();

  final class Factory extends BaseFactory<SHA512_224> {

    private Factory() {
      super("SHA-512/224");
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
    public long getMultiHashFnCode() {
      return Long.MIN_VALUE;
    }

    @Override
    public SHA512_224 overlay(final byte[] digest) {
      return new SHA512_224Value(digest);
    }

    @Override
    public SHA512_224 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetSHA512_224(digest, offset);
    }

    @Override
    public SHA512_224 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSHA512_224(digest, offset);
    }

    @Override
    public String toString() {
      return "SHA512_224.Factory - 28 byte digest";
    }
  }
}