package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSM3;
import systems.comodal.hash.gen.SM3Value;
import systems.comodal.hash.gen.LittleEndianOffsetSM3;

public interface SM3 extends Hash {

  HashFactory<SM3> FACTORY = new SM3.Factory();

  final class Factory extends BaseFactory<SM3> {

    private Factory() {
      super("SM3");
    }

    @Override
    public int getDigestLength() {
      return 32;
    }

    @Override
    public SM3 overlay(final byte[] digest) {
      return new SM3Value(digest);
    }

    @Override
    public SM3 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSM3(digest, offset);
    }

    @Override
    public SM3 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSM3(digest, offset);
    }

    @Override
    public String toString() {
      return "SM3.Factory - 32 byte digest";
    }
  }
}