package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSkein256_128;
import systems.comodal.hash.gen.Skein256_128Value;
import systems.comodal.hash.gen.ReverseSkein256_128;

public interface Skein256_128 extends Hash {

  HashFactory<Skein256_128> FACTORY = new Skein256_128.Factory();

  final class Factory extends BaseFactory<Skein256_128> {

    private Factory() {
      super("Skein-256-128");
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
    public Skein256_128 overlay(final byte[] digest) {
      return new Skein256_128Value(digest);
    }

    @Override
    public Skein256_128 overlay(final byte[] digest, final int offset) {
      return new OffsetSkein256_128(digest, offset);
    }

    @Override
    public Skein256_128 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSkein256_128(digest, offset);
    }

    @Override
    public String toString() {
      return "Skein256_128.Factory - 16 byte digest";
    }
  }
}