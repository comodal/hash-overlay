package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.TIGER;

public final class DiscreteTIGER extends DiscreteHash implements TIGER {

  public DiscreteTIGER(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof TIGER
        && ((Hash) other).equals(data);
  }
}