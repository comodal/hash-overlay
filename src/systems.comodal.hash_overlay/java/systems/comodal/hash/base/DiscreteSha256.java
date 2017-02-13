package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Sha256;

public final class DiscreteSha256 extends DiscreteHash implements Sha256 {

  public DiscreteSha256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Sha256
        && ((Hash) other).equals(data);
  }
}