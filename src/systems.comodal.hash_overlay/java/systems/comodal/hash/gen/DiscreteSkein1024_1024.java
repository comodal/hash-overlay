package systems.comodal.hash.base;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.Skein1024_1024;

public final class DiscreteSkein1024_1024 extends DiscreteHash implements Skein1024_1024 {

  public DiscreteSkein1024_1024(final byte[] data) {
    super(data);
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null
        && other instanceof Skein1024_1024
        && ((Hash) other).equals(data);
  }
}