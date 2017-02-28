package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BigEndianOffsetHash;

public final class BigEndianOffsetSHA extends BigEndianOffsetHash implements SHA {

  public BigEndianOffsetSHA(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA
        && ((Hash) other).digestEquals(data, offset);
  }
}