package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_160;

public final class DiscreteSkein512_160 extends DiscreteHash implements Skein512_160 {

  public DiscreteSkein512_160(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein512_160
        && ((Hash) other).equals(data);
  }
}