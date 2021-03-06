package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetMD4;
import systems.comodal.hash.gen.MD4Value;
import systems.comodal.hash.gen.ReverseMD4;

public interface MD4 extends Hash {

  HashFactory<MD4> FACTORY = new MD4.Factory();

  final class Factory extends BaseFactory<MD4> {

    private Factory() {
      super("MD4");
    }

    @Override
    public int getDigestLength() {
      return 16;
    }

    @Override
    public int getOffsetLength() {
      return 15;
    }

    @Override
    public long getMultiHashFnCode() {
      return Long.MIN_VALUE;
    }

    @Override
    public MD4 overlay(final byte[] digest) {
      return new MD4Value(digest);
    }

    @Override
    public MD4 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetMD4(digest, offset);
    }

    @Override
    public MD4 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseMD4(digest, offset);
    }

    @Override
    public String toString() {
      return "MD4.Factory - 16 byte digest";
    }
  }
}