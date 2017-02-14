package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD5;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteMD5 extends DiscreteHash implements MD5 {

  public DiscreteMD5(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD5
        && ((Hash) other).equals(data);
  }
}