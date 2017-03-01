package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.TIGER;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.OffsetHash;

public final class OffsetTIGER extends OffsetHash implements TIGER {

  public OffsetTIGER(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<TIGER> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof TIGER
        && ((Hash) other).digestEquals(data, offset);
  }
}