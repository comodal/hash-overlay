package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD128;

public final class DiscreteRIPEMD128 extends DiscreteHash implements RIPEMD128 {

  public DiscreteRIPEMD128(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof RIPEMD128
        && ((Hash) other).equals(data);
  }
}