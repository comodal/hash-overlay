package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA512_224;

public final class DiscreteSHA512_224 extends DiscreteHash implements SHA512_224 {

  public DiscreteSHA512_224(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof SHA512_224
        && ((Hash) other).equals(data);
  }
}