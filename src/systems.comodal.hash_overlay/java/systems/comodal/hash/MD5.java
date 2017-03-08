package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetMD5;
import systems.comodal.hash.gen.MD5Value;
import systems.comodal.hash.gen.ReverseMD5;

public interface MD5 extends Hash {

  HashFactory<MD5> FACTORY = new MD5.Factory();

  final class Factory extends BaseFactory<MD5> {

    private Factory() {
      super("MD5");
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
    public MD5 overlay(final byte[] digest) {
      return new MD5Value(digest);
    }

    @Override
    public MD5 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetMD5(digest, offset);
    }

    @Override
    public MD5 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseMD5(digest, offset);
    }

    @Override
    public String toString() {
      return "MD5.Factory - 16 byte digest";
    }
  }
}