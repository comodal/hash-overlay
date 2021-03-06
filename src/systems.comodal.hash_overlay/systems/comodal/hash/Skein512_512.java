package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSkein512_512;
import systems.comodal.hash.gen.Skein512_512Value;
import systems.comodal.hash.gen.ReverseSkein512_512;

public interface Skein512_512 extends Hash {

  HashFactory<Skein512_512> FACTORY = new Skein512_512.Factory();

  final class Factory extends BaseFactory<Skein512_512> {

    private Factory() {
      super("Skein-512-512");
    }

    @Override
    public int getDigestLength() {
      return 64;
    }

    @Override
    public int getOffsetLength() {
      return 63;
    }

    @Override
    public long getMultiHashFnCode() {
      return Long.MIN_VALUE;
    }

    @Override
    public Skein512_512 overlay(final byte[] digest) {
      return new Skein512_512Value(digest);
    }

    @Override
    public Skein512_512 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetSkein512_512(digest, offset);
    }

    @Override
    public Skein512_512 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSkein512_512(digest, offset);
    }

    @Override
    public String toString() {
      return "Skein512_512.Factory - 64 byte digest";
    }
  }
}