package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_128;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteSkein512_128 extends DiscreteHash implements Skein512_128 {

  public DiscreteSkein512_128(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein512_128
        && ((Hash) other).equals(data);
  }
}