package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD2;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.ReverseHash;

public final class ReverseMD2 extends ReverseHash implements MD2 {

  public ReverseMD2(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<MD2> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD2
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}