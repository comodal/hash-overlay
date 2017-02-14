package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B512;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteBLAKE2B512 extends DiscreteHash implements BLAKE2B512 {

  public DiscreteBLAKE2B512(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof BLAKE2B512
        && ((Hash) other).equals(data);
  }
}