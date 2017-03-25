package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSkein1024_384;
import systems.comodal.hash.gen.Skein1024_384Value;
import systems.comodal.hash.gen.ReverseSkein1024_384;

public interface Skein1024_384 extends Hash {

  HashFactory<Skein1024_384> FACTORY = new Skein1024_384.Factory();

  final class Factory extends BaseFactory<Skein1024_384> {

    private Factory() {
      super("Skein-1024-384");
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
      return Long.MIN_VALUE;
    }

    @Override
    public Skein1024_384 overlay(final byte[] digest) {
      return new Skein1024_384Value(digest);
    }

    @Override
    public Skein1024_384 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetSkein1024_384(digest, offset);
    }

    @Override
    public Skein1024_384 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSkein1024_384(digest, offset);
    }

    @Override
    public String toString() {
      return "Skein1024_384.Factory - 48 byte digest";
    }
  }
}