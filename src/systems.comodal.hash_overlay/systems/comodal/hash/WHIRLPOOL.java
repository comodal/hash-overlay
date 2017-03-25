package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetWHIRLPOOL;
import systems.comodal.hash.gen.WHIRLPOOLValue;
import systems.comodal.hash.gen.ReverseWHIRLPOOL;

public interface WHIRLPOOL extends Hash {

  HashFactory<WHIRLPOOL> FACTORY = new WHIRLPOOL.Factory();

  final class Factory extends BaseFactory<WHIRLPOOL> {

    private Factory() {
      super("WHIRLPOOL");
    }

    @Override
    public int getDigestLength() {
      return 64;
    }

    @Override
    public int getOffsetLength() {
      return 63;
    }

    @Override
    public long getMultiHashFnCode() {
      return Long.MIN_VALUE;
    }

    @Override
    public WHIRLPOOL overlay(final byte[] digest) {
      return new WHIRLPOOLValue(digest);
    }

    @Override
    public WHIRLPOOL overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetWHIRLPOOL(digest, offset);
    }

    @Override
    public WHIRLPOOL reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseWHIRLPOOL(digest, offset);
    }

    @Override
    public String toString() {
      return "WHIRLPOOL.Factory - 64 byte digest";
    }
  }
}