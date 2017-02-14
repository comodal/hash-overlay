package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA384;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteSHA384 extends DiscreteHash implements SHA384 {

  public DiscreteSHA384(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA384
        && ((Hash) other).equals(data);
  }
}