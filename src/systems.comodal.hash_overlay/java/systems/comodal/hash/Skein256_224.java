package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSkein256_224;
import systems.comodal.hash.gen.Skein256_224Value;
import systems.comodal.hash.gen.LittleEndianOffsetSkein256_224;

public interface Skein256_224 extends Hash {

  HashFactory<Skein256_224> FACTORY = new Skein256_224.Factory();

  final class Factory extends BaseFactory<Skein256_224> {

    private Factory() {
      super("Skein-256-224");
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
    public Skein256_224 overlay(final byte[] digest) {
      return new Skein256_224Value(digest);
    }

    @Override
    public Skein256_224 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein256_224(digest, offset);
    }

    @Override
    public Skein256_224 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein256_224(digest, offset);
    }

    @Override
    public String toString() {
      return "Skein256_224.Factory - 28 byte digest";
    }
  }
}