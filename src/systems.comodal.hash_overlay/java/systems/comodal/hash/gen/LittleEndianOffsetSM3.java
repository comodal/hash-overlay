package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SM3;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetSM3 extends LittleEndianOffsetHash implements SM3 {

  public LittleEndianOffsetSM3(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SM3> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SM3
        && ((Hash) other).equalsReverse(data, offset);
  }
}