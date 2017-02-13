package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SM3;

public final class DiscreteSM3 extends DiscreteHash implements SM3 {

  public DiscreteSM3(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SM3
        && ((Hash) other).equals(data);
  }
}