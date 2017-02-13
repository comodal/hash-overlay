package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD160;

public final class DiscreteRIPEMD160 extends DiscreteHash implements RIPEMD160 {

  public DiscreteRIPEMD160(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof RIPEMD160
        && ((Hash) other).equals(data);
  }
}