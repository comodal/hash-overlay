package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSkein256_160;
import systems.comodal.hash.gen.Skein256_160Value;
import systems.comodal.hash.gen.LittleEndianOffsetSkein256_160;

public interface Skein256_160 extends Hash {

  HashFactory<Skein256_160> FACTORY = new Skein256_160.Factory();

  final class Factory extends BaseFactory<Skein256_160> {

    private Factory() {
      super("Skein-256-160");
    }

    @Override
    public int getDigestLength() {
      return 20;
    }

    @Override
    public int getOffsetLength() {
      return 19;
    }

    @Override
    public Skein256_160 overlay(final byte[] digest) {
      return new Skein256_160Value(digest);
    }

    @Override
    public Skein256_160 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein256_160(digest, offset);
    }

    @Override
    public Skein256_160 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein256_160(digest, offset);
    }

    @Override
    public String toString() {
      return "Skein256_160.Factory - 20 byte digest";
    }
  }
}