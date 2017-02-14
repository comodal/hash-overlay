package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK288;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteKECCAK288 extends DiscreteHash implements KECCAK288 {

  public DiscreteKECCAK288(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK288
        && ((Hash) other).equals(data);
  }
}