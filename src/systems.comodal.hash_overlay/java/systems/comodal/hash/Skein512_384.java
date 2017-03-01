package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSkein512_384;
import systems.comodal.hash.gen.Skein512_384Value;
import systems.comodal.hash.gen.ReverseSkein512_384;

public interface Skein512_384 extends Hash {

  HashFactory<Skein512_384> FACTORY = new Skein512_384.Factory();

  final class Factory extends BaseFactory<Skein512_384> {

    private Factory() {
      super("Skein-512-384");
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
    public Skein512_384 overlay(final byte[] digest) {
      return new Skein512_384Value(digest);
    }

    @Override
    public Skein512_384 overlay(final byte[] digest, final int offset) {
      return new OffsetSkein512_384(digest, offset);
    }

    @Override
    public Skein512_384 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSkein512_384(digest, offset);
    }

    @Override
    public String toString() {
      return "Skein512_384.Factory - 48 byte digest";
    }
  }
}