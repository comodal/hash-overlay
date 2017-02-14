package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA224;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteSHA224 extends DiscreteHash implements SHA224 {

  public DiscreteSHA224(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA224
        && ((Hash) other).equals(data);
  }
}