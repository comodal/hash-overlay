package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD2;

public final class DiscreteMD2 extends DiscreteHash implements MD2 {

  public DiscreteMD2(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof MD2
        && ((Hash) other).equals(data);
  }
}