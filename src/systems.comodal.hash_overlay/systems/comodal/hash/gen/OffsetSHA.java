package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetSHA extends OffsetHash implements SHA {

  public OffsetSHA(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA
        && ((Hash) other).digestEquals(data, offset);
  }
}