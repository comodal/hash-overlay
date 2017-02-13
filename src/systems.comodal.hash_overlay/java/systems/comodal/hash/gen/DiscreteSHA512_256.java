package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512_256;

public final class DiscreteSHA512_256 extends DiscreteHash implements SHA512_256 {

  public DiscreteSHA512_256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA512_256
        && ((Hash) other).equals(data);
  }
}