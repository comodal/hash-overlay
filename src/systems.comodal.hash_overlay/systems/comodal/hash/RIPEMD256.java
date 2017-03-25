package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetRIPEMD256;
import systems.comodal.hash.gen.RIPEMD256Value;
import systems.comodal.hash.gen.ReverseRIPEMD256;

public interface RIPEMD256 extends Hash {

  HashFactory<RIPEMD256> FACTORY = new RIPEMD256.Factory();

  final class Factory extends BaseFactory<RIPEMD256> {

    private Factory() {
      super("RIPEMD256");
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
    public RIPEMD256 overlay(final byte[] digest) {
      return new RIPEMD256Value(digest);
    }

    @Override
    public RIPEMD256 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetRIPEMD256(digest, offset);
    }

    @Override
    public RIPEMD256 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseRIPEMD256(digest, offset);
    }

    @Override
    public String toString() {
      return "RIPEMD256.Factory - 32 byte digest";
    }
  }
}