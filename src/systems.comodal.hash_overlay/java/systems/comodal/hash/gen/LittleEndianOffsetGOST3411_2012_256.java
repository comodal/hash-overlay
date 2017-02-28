package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.GOST3411_2012_256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetGOST3411_2012_256 extends LittleEndianOffsetHash implements GOST3411_2012_256 {

  public LittleEndianOffsetGOST3411_2012_256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<GOST3411_2012_256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof GOST3411_2012_256
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}