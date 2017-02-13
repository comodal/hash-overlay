package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B160;

public final class DiscreteBLAKE2B160 extends DiscreteHash implements BLAKE2B160 {

  public DiscreteBLAKE2B160(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof BLAKE2B160
        && ((Hash) other).equals(data);
  }
}