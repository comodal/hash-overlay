package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetMD2;
import systems.comodal.hash.gen.DiscreteMD2;
import systems.comodal.hash.gen.LittleEndianOffsetMD2;

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
    public MD2 overlay(final byte[] digest) {
      return new DiscreteMD2(digest);
    }

    @Override
    public MD2 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetMD2(digest, offset);
    }

    @Override
    public MD2 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetMD2(digest, offset);
    }

    @Override
    public String toString() {
      return "MD2.Factory - 16 byte digest";
    }
  }
}