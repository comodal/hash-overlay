package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_256;

public final class DiscreteSHA3_256 extends DiscreteHash implements SHA3_256 {

  public DiscreteSHA3_256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA3_256
        && ((Hash) other).equals(data);
  }
}