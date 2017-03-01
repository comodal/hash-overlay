package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSHA224;
import systems.comodal.hash.gen.SHA224Value;
import systems.comodal.hash.gen.ReverseSHA224;

public interface SHA224 extends Hash {

  HashFactory<SHA224> FACTORY = new SHA224.Factory();

  final class Factory extends BaseFactory<SHA224> {

    private Factory() {
      super("SHA-224");
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
    public SHA224 overlay(final byte[] digest) {
      return new SHA224Value(digest);
    }

    @Override
    public SHA224 overlay(final byte[] digest, final int offset) {
      return new OffsetSHA224(digest, offset);
    }

    @Override
    public SHA224 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSHA224(digest, offset);
    }

    @Override
    public String toString() {
      return "SHA224.Factory - 28 byte digest";
    }
  }
}