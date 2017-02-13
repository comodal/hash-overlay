package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA;

public final class DiscreteSHA extends DiscreteHash implements SHA {

  public DiscreteSHA(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA
        && ((Hash) other).equals(data);
  }
}