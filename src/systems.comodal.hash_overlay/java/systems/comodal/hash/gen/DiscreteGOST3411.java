package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.GOST3411;

public final class DiscreteGOST3411 extends DiscreteHash implements GOST3411 {

  public DiscreteGOST3411(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof GOST3411
        && ((Hash) other).equals(data);
  }
}