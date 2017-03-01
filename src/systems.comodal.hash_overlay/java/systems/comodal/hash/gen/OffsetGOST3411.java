package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.GOST3411;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetGOST3411 extends OffsetHash implements GOST3411 {

  public OffsetGOST3411(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<GOST3411> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof GOST3411
        && ((Hash) other).digestEquals(data, offset);
  }
}