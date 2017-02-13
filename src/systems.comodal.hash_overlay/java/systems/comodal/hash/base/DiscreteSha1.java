package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Sha1;

public final class DiscreteSha1 extends DiscreteHash implements Sha1 {

  public DiscreteSha1(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Sha1
        && ((Hash) other).equals(data);
  }
}