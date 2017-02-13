package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_128;

public final class DiscreteSkein256_128 extends DiscreteHash implements Skein256_128 {

  public DiscreteSkein256_128(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein256_128
        && ((Hash) other).equals(data);
  }
}