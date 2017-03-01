package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetRIPEMD320;
import systems.comodal.hash.gen.RIPEMD320Value;
import systems.comodal.hash.gen.ReverseRIPEMD320;

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
    public int getOffsetLength() {
      return 39;
    }

    @Override
    public long getMultiHashFnCode() {
      return Long.MIN_VALUE;
    }

    @Override
    public RIPEMD320 overlay(final byte[] digest) {
      return new RIPEMD320Value(digest);
    }

    @Override
    public RIPEMD320 overlay(final byte[] digest, final int offset) {
      return new OffsetRIPEMD320(digest, offset);
    }

    @Override
    public RIPEMD320 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseRIPEMD320(digest, offset);
    }

    @Override
    public String toString() {
      return "RIPEMD320.Factory - 40 byte digest";
    }
  }
}