package systems.comodal.hash;

import systems.comodal.hash.base.BaseFactory;
import systems.comodal.hash.base.BigEndianOffsetRipeMd160;
import systems.comodal.hash.base.DiscreteRipeMd160;
import systems.comodal.hash.base.LittleEndianOffsetRipeMd160;

public interface RipeMd160 extends Hash {

  HashFactory<RipeMd160> FACTORY = new Factory();

  @Override
  default HashFactory getFactory() {
    return FACTORY;
  }

  class Factory extends BaseFactory<RipeMd160> {

    private Factory() {
      super("RIPEMD160");
    }

    @Override
    public int getDigestLength() {
      return 20;
    }

    @Override
    public RipeMd160 overlay(final byte[] digest) {
      return new DiscreteRipeMd160(digest);
    }

    @Override
    public RipeMd160 overlay(final byte[] digest, final int offset) {
      return new BigEndianOffsetRipeMd160(digest, offset);
    }

    @Override
    public RipeMd160 reverseOverlay(final byte[] digest, final int offset) {
      return new LittleEndianOffsetRipeMd160(digest, offset);
    }
  }
}