package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.BLAKE2B256;

public final class DiscreteBLAKE2B256 extends DiscreteHash implements BLAKE2B256 {

  public DiscreteBLAKE2B256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof BLAKE2B256
        && ((Hash) other).equals(data);
  }
}