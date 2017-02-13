package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Sha256;

final class DiscreteSha256 extends Discrete256 implements Sha256 {

  DiscreteSha256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Sha256
        && ((Hash) other).equals(data);
  }
}
