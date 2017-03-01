package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetSHA;
import systems.comodal.hash.gen.SHAValue;
import systems.comodal.hash.gen.ReverseSHA;

public interface SHA extends Hash {

  HashFactory<SHA> FACTORY = new SHA.Factory();

  final class Factory extends BaseFactory<SHA> {

    private Factory() {
      super("SHA");
    }

    @Override
    public int getDigestLength() {
      return 20;
    }

    @Override
    public int getOffsetLength() {
      return 19;
    }

    @Override
    public long getMultiHashFnCode() {
      return Long.MIN_VALUE;
    }

    @Override
    public SHA overlay(final byte[] digest) {
      return new SHAValue(digest);
    }

    @Override
    public SHA overlay(final byte[] digest, final int offset) {
      return new OffsetSHA(digest, offset);
    }

    @Override
    public SHA reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseSHA(digest, offset);
    }

    @Override
    public String toString() {
      return "SHA.Factory - 20 byte digest";
    }
  }
}