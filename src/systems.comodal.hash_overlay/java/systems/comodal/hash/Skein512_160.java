package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSkein512_160;
import systems.comodal.hash.gen.Skein512_160Value;
import systems.comodal.hash.gen.LittleEndianOffsetSkein512_160;

public interface Skein512_160 extends Hash {

  HashFactory<Skein512_160> FACTORY = new Skein512_160.Factory();

  final class Factory extends BaseFactory<Skein512_160> {

    private Factory() {
      super("Skein-512-160");
    }

    @Override
    public int getDigestLength() {
      return 20;
    }

    @Override
    public Skein512_160 overlay(final byte[] digest) {
      return new Skein512_160Value(digest);
    }

    @Override
    public Skein512_160 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSkein512_160(digest, offset);
    }

    @Override
    public Skein512_160 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSkein512_160(digest, offset);
    }

    @Override
    public String toString() {
      return "Skein512_160.Factory - 20 byte digest";
    }
  }
}