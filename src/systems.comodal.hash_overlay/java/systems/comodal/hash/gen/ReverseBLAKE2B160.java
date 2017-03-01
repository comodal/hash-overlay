package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B160;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseBLAKE2B160 extends ReverseHash implements BLAKE2B160 {

  public ReverseBLAKE2B160(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<BLAKE2B160> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof BLAKE2B160
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}