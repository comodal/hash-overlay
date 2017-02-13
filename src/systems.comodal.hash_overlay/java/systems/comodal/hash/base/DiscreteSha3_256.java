package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Sha3_256;

final class DiscreteSha3_256 extends Discrete256 implements Sha3_256 {

  DiscreteSha3_256(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Sha3_256
        && ((Hash) other).equals(data);
  }
}
