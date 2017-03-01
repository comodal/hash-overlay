package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.TIGER;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseTIGER extends ReverseHash implements TIGER {

  public ReverseTIGER(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<TIGER> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof TIGER
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}