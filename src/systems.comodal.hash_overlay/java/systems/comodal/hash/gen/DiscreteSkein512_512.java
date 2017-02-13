package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_512;

public final class DiscreteSkein512_512 extends DiscreteHash implements Skein512_512 {

  public DiscreteSkein512_512(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein512_512
        && ((Hash) other).equals(data);
  }
}