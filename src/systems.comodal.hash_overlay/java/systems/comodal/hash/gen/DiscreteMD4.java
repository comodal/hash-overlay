package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD4;

public final class DiscreteMD4 extends DiscreteHash implements MD4 {

  public DiscreteMD4(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof MD4
        && ((Hash) other).equals(data);
  }
}