package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA3_512;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteSHA3_512 extends DiscreteHash implements SHA3_512 {

  public DiscreteSHA3_512(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA3_512
        && ((Hash) other).equals(data);
  }
}