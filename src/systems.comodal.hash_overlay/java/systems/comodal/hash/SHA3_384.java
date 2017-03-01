package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSHA3_384;
import systems.comodal.hash.gen.SHA3_384Value;
import systems.comodal.hash.gen.ReverseSHA3_384;

public interface SHA3_384 extends Hash {

  HashFactory<SHA3_384> FACTORY = new SHA3_384.Factory();

  final class Factory extends BaseFactory<SHA3_384> {

    private Factory() {
      super("SHA3-384");
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
    public long getMultiHashFnCode() {
      return 0x15;
    }

    @Override
    public SHA3_384 overlay(final byte[] digest) {
      return new SHA3_384Value(digest);
    }

    @Override
    public SHA3_384 overlay(final byte[] digest, final int offset) {
      return new OffsetSHA3_384(digest, offset);
    }

    @Override
    public SHA3_384 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSHA3_384(digest, offset);
    }

    @Override
    public String toString() {
      return "SHA3_384.Factory - 48 byte digest";
    }
  }
}