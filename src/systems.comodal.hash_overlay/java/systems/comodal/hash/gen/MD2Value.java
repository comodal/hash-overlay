package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD2;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.HashValue;

public final class MD2Value extends HashValue implements MD2 {

  public MD2Value(final byte[] data) {
    super(data);
  }

  @Override
  public HashFactory<MD2> getHashFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD2
        && ((Hash) other).digestEquals(data);
  }
}