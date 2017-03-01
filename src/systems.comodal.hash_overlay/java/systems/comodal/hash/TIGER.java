package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetTIGER;
import systems.comodal.hash.gen.TIGERValue;
import systems.comodal.hash.gen.ReverseTIGER;

public interface TIGER extends Hash {

  HashFactory<TIGER> FACTORY = new TIGER.Factory();

  final class Factory extends BaseFactory<TIGER> {

    private Factory() {
      super("TIGER");
    }

    @Override
    public int getDigestLength() {
      return 24;
    }

    @Override
    public int getOffsetLength() {
      return 23;
    }

    @Override
    public long getMultiHashFnCode() {
      return Long.MIN_VALUE;
    }

    @Override
    public TIGER overlay(final byte[] digest) {
      return new TIGERValue(digest);
    }

    @Override
    public TIGER overlay(final byte[] digest, final int offset) {
      return new OffsetTIGER(digest, offset);
    }

    @Override
    public TIGER reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseTIGER(digest, offset);
    }

    @Override
    public String toString() {
      return "TIGER.Factory - 24 byte digest";
    }
  }
}