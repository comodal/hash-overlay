package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK384;

public final class DiscreteKECCAK384 extends DiscreteHash implements KECCAK384 {

  public DiscreteKECCAK384(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof KECCAK384
        && ((Hash) other).equals(data);
  }
}