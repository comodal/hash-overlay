package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetRIPEMD128;
import systems.comodal.hash.gen.RIPEMD128Value;
import systems.comodal.hash.gen.LittleEndianOffsetRIPEMD128;

public interface RIPEMD128 extends Hash {

  HashFactory<RIPEMD128> FACTORY = new RIPEMD128.Factory();

  final class Factory extends BaseFactory<RIPEMD128> {

    private Factory() {
      super("RIPEMD128");
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
    public RIPEMD128 overlay(final byte[] digest) {
      return new RIPEMD128Value(digest);
    }

    @Override
    public RIPEMD128 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetRIPEMD128(digest, offset);
    }

    @Override
    public RIPEMD128 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetRIPEMD128(digest, offset);
    }

    @Override
    public String toString() {
      return "RIPEMD128.Factory - 16 byte digest";
    }
  }
}