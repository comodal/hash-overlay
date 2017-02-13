package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_224;

public final class DiscreteSHA3_224 extends DiscreteHash implements SHA3_224 {

  public DiscreteSHA3_224(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA3_224
        && ((Hash) other).equals(data);
  }
}