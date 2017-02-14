package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK256;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteKECCAK256 extends DiscreteHash implements KECCAK256 {

  public DiscreteKECCAK256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK256
        && ((Hash) other).equals(data);
  }
}