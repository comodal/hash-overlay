package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA1;

public final class DiscreteSHA1 extends DiscreteHash implements SHA1 {

  public DiscreteSHA1(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA1
        && ((Hash) other).equals(data);
  }
}