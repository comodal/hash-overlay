package systems.comodal.hash.base;

import systems.comodal.hash.Hash;
import systems.comodal.hash.Md5;

public final class DiscreteMd5 extends DiscreteHash implements Md5 {

  public DiscreteMd5(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Md5
        && ((Hash) other).equals(data);
  }
}
