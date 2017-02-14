package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK224;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteKECCAK224 extends DiscreteHash implements KECCAK224 {

  public DiscreteKECCAK224(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK224
        && ((Hash) other).equals(data);
  }
}