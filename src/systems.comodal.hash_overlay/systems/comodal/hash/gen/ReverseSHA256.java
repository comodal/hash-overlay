package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseSHA256 extends ReverseHash implements SHA256 {

  public ReverseSHA256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA256> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA256
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}