package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.RIPEMD320;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteRIPEMD320 extends DiscreteHash implements RIPEMD320 {

  public DiscreteRIPEMD320(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof RIPEMD320
        && ((Hash) other).equals(data);
  }
}