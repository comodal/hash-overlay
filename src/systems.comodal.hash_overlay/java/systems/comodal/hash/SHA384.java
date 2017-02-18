package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSHA384;
import systems.comodal.hash.gen.SHA384Value;
import systems.comodal.hash.gen.LittleEndianOffsetSHA384;

public interface SHA384 extends Hash {

  HashFactory<SHA384> FACTORY = new SHA384.Factory();

  final class Factory extends BaseFactory<SHA384> {

    private Factory() {
      super("SHA-384");
    }

    @Override
    public int getDigestLength() {
      return 48;
    }

    @Override
    public int getOffsetLength() {
      return 47;
    }

    @Override
    public SHA384 overlay(final byte[] digest) {
      return new SHA384Value(digest);
    }

    @Override
    public SHA384 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA384(digest, offset);
    }

    @Override
    public SHA384 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA384(digest, offset);
    }

    @Override
    public String toString() {
      return "SHA384.Factory - 48 byte digest";
    }
  }
}