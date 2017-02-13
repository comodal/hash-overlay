package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Sha3_256;

public final class DiscreteSha3_256 extends DiscreteHash implements Sha3_256 {

  public DiscreteSha3_256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Sha3_256
        && ((Hash) other).equals(data);
  }
}