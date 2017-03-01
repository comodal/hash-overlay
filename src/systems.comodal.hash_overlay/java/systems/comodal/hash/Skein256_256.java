package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSkein256_256;
import systems.comodal.hash.gen.Skein256_256Value;
import systems.comodal.hash.gen.ReverseSkein256_256;

public interface Skein256_256 extends Hash {

  HashFactory<Skein256_256> FACTORY = new Skein256_256.Factory();

  final class Factory extends BaseFactory<Skein256_256> {

    private Factory() {
      super("Skein-256-256");
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
      return Long.MIN_VALUE;
    }

    @Override
    public Skein256_256 overlay(final byte[] digest) {
      return new Skein256_256Value(digest);
    }

    @Override
    public Skein256_256 overlay(final byte[] digest, final int offset) {
      return new OffsetSkein256_256(digest, offset);
    }

    @Override
    public Skein256_256 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSkein256_256(digest, offset);
    }

    @Override
    public String toString() {
      return "Skein256_256.Factory - 32 byte digest";
    }
  }
}