package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetRIPEMD320;
import systems.comodal.hash.gen.DiscreteRIPEMD320;
import systems.comodal.hash.gen.LittleEndianOffsetRIPEMD320;

public interface RIPEMD320 extends Hash {

  HashFactory<RIPEMD320> FACTORY = new RIPEMD320.Factory();

  final class Factory extends BaseFactory<RIPEMD320> {

    private Factory() {
      super("RIPEMD320");
    }

    @Override
    public int getDigestLength() {
      return 40;
    }

    @Override
    public RIPEMD320 overlay(final byte[] digest) {
      return new DiscreteRIPEMD320(digest);
    }

    @Override
    public RIPEMD320 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetRIPEMD320(digest, offset);
    }

    @Override
    public RIPEMD320 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetRIPEMD320(digest, offset);
    }

    @Override
    public String toString() {
      return "RIPEMD320.Factory - 40 byte digest";
    }
  }
}