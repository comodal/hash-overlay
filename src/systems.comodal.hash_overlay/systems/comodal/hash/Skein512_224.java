package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSkein512_224;
import systems.comodal.hash.gen.Skein512_224Value;
import systems.comodal.hash.gen.ReverseSkein512_224;

public interface Skein512_224 extends Hash {

  HashFactory<Skein512_224> FACTORY = new Skein512_224.Factory();

  final class Factory extends BaseFactory<Skein512_224> {

    private Factory() {
      super("Skein-512-224");
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
    public Skein512_224 overlay(final byte[] digest) {
      return new Skein512_224Value(digest);
    }

    @Override
    public Skein512_224 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetSkein512_224(digest, offset);
    }

    @Override
    public Skein512_224 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSkein512_224(digest, offset);
    }

    @Override
    public String toString() {
      return "Skein512_224.Factory - 28 byte digest";
    }
  }
}