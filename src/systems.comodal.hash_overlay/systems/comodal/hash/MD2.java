package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.OffsetMD2;
import systems.comodal.hash.gen.MD2Value;
import systems.comodal.hash.gen.ReverseMD2;

public interface MD2 extends Hash {

  HashFactory<MD2> FACTORY = new MD2.Factory();

  final class Factory extends BaseFactory<MD2> {

    private Factory() {
      super("MD2");
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
    public MD2 overlay(final byte[] digest) {
      return new MD2Value(digest);
    }

    @Override
    public MD2 overlay(final byte[] digest, final int offset) {
      return offset == 0 && digest.length == getDigestLength()
          ? overlay(digest) : new OffsetMD2(digest, offset);
    }

    @Override
    public MD2 reverseOverlay(final byte[] digest, final int offset) {
      return new ReverseMD2(digest, offset);
    }

    @Override
    public String toString() {
      return "MD2.Factory - 16 byte digest";
    }
  }
}