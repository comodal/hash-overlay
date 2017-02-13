package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512;

public final class DiscreteSHA512 extends DiscreteHash implements SHA512 {

  public DiscreteSHA512(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA512
        && ((Hash) other).equals(data);
  }
}