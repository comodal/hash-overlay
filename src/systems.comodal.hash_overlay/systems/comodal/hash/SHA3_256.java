package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSHA3_256;
import systems.comodal.hash.gen.SHA3_256Value;
import systems.comodal.hash.gen.ReverseSHA3_256;

public interface SHA3_256 extends Hash {

  HashFactory<SHA3_256> FACTORY = new SHA3_256.Factory();

  final class Factory extends BaseFactory<SHA3_256> {

    private Factory() {
      super("SHA3-256");
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
      return 0x16;
    }

    @Override
    public SHA3_256 overlay(final byte[] digest) {
      return new SHA3_256Value(digest);
    }

    @Override
    public SHA3_256 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetSHA3_256(digest, offset);
    }

    @Override
    public SHA3_256 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSHA3_256(digest, offset);
    }

    @Override
    public String toString() {
      return "SHA3_256.Factory - 32 byte digest";
    }
  }
}