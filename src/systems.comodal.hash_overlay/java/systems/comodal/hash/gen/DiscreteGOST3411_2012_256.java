package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.GOST3411_2012_256;

public final class DiscreteGOST3411_2012_256 extends DiscreteHash implements GOST3411_2012_256 {

  public DiscreteGOST3411_2012_256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof GOST3411_2012_256
        && ((Hash) other).equals(data);
  }
}