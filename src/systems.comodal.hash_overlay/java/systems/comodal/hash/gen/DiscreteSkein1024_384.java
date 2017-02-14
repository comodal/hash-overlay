package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_384;
import systems.comodal.hash.base.DiscreteHash;

public final class DiscreteSkein1024_384 extends DiscreteHash implements Skein1024_384 {

  public DiscreteSkein1024_384(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof Skein1024_384
        && ((Hash) other).equals(data);
  }
}