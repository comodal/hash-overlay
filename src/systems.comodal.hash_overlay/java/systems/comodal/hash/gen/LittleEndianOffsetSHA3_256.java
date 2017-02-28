package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetSHA3_256 extends LittleEndianOffsetHash implements SHA3_256 {

  public LittleEndianOffsetSHA3_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA3_256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA3_256
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}