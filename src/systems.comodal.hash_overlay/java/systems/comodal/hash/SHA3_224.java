package systems.comodal.hash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.gen.BigEndianOffsetSHA3_224;
import systems.comodal.hash.gen.SHA3_224Value;
import systems.comodal.hash.gen.LittleEndianOffsetSHA3_224;

public interface SHA3_224 extends Hash {

  HashFactory<SHA3_224> FACTORY = new SHA3_224.Factory();

  final class Factory extends BaseFactory<SHA3_224> {

    private Factory() {
      super("SHA3-224");
    }

    @Override
    public int getDigestLength() {
      return 28;
    }

    @Override
    public int getOffsetLength() {
      return 27;
    }

    @Override
    public long getMultiHashFnCode() {
      return 0x17;
    }

    @Override
    public SHA3_224 overlay(final byte[] digest) {
      return new SHA3_224Value(digest);
    }

    @Override
    public SHA3_224 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetSHA3_224(digest, offset);
    }

    @Override
    public SHA3_224 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetSHA3_224(digest, offset);
    }

    @Override
    public String toString() {
      return "SHA3_224.Factory - 28 byte digest";
    }
  }
}