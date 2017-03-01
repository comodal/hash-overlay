package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetGOST3411_2012_512;
import systems.comodal.hash.gen.GOST3411_2012_512Value;
import systems.comodal.hash.gen.ReverseGOST3411_2012_512;

public interface GOST3411_2012_512 extends Hash {

  HashFactory<GOST3411_2012_512> FACTORY = new GOST3411_2012_512.Factory();

  final class Factory extends BaseFactory<GOST3411_2012_512> {

    private Factory() {
      super("GOST3411-2012-512");
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
    public GOST3411_2012_512 overlay(final byte[] digest) {
      return new GOST3411_2012_512Value(digest);
    }

    @Override
    public GOST3411_2012_512 overlay(final byte[] digest, final int offset) {
      return new OffsetGOST3411_2012_512(digest, offset);
    }

    @Override
    public GOST3411_2012_512 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseGOST3411_2012_512(digest, offset);
    }

    @Override
    public String toString() {
      return "GOST3411_2012_512.Factory - 64 byte digest";
    }
  }
}