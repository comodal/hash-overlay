package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein256_224;

public final class DiscreteSkein256_224 extends DiscreteHash implements Skein256_224 {

  public DiscreteSkein256_224(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein256_224
        && ((Hash) other).equals(data);
  }
}