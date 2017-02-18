package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSkein1024_1024;
import systems.comodal.hash.gen.Skein1024_1024Value;
import systems.comodal.hash.gen.LittleEndianOffsetSkein1024_1024;

public interface Skein1024_1024 extends Hash {

  HashFactory<Skein1024_1024> FACTORY = new Skein1024_1024.Factory();

  final class Factory extends BaseFactory<Skein1024_1024> {

    private Factory() {
      super("Skein-1024-1024");
    }

    @Override
    public int getDigestLength() {
      return 128;
    }

    @Override
    public int getOffsetLength() {
      return 127;
    }

    @Override
    public Skein1024_1024 overlay(final byte[] digest) {
      return new Skein1024_1024Value(digest);
    }

    @Override
    public Skein1024_1024 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein1024_1024(digest, offset);
    }

    @Override
    public Skein1024_1024 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein1024_1024(digest, offset);
    }

    @Override
    public String toString() {
      return "Skein1024_1024.Factory - 128 byte digest";
    }
  }
}