package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.RipeMd160;

public final class DiscreteRipeMd160 extends DiscreteHash implements RipeMd160 {

  public DiscreteRipeMd160(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof RipeMd160
        && ((Hash) other).equals(data);
  }
}
