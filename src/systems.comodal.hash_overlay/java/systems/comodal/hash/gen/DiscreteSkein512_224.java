package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_224;

public final class DiscreteSkein512_224 extends DiscreteHash implements Skein512_224 {

  public DiscreteSkein512_224(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein512_224
        && ((Hash) other).equals(data);
  }
}