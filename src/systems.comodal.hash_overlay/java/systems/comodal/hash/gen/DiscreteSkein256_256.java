package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_256;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteSkein256_256 extends DiscreteHash implements Skein256_256 {

  public DiscreteSkein256_256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein256_256
        && ((Hash) other).equals(data);
  }
}