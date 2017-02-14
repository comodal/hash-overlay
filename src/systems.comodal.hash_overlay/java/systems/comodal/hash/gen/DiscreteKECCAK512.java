package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK512;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteKECCAK512 extends DiscreteHash implements KECCAK512 {

  public DiscreteKECCAK512(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK512
        && ((Hash) other).equals(data);
  }
}