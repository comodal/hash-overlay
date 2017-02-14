package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein512_256;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteSkein512_256 extends DiscreteHash implements Skein512_256 {

  public DiscreteSkein512_256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein512_256
        && ((Hash) other).equals(data);
  }
}