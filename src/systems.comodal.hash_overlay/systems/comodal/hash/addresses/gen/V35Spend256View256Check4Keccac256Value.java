package systems.comodal.hash.addresses.gen;

import java.util.Arrays;
import systems.comodal.hash.addresses.Spend256View256Check4Keccac256;
import systems.comodal.hash.addresses.api.Addr;
import systems.comodal.hash.addresses.api.AddrFactory;
import systems.comodal.hash.addresses.base.AddrValue;

public final class V35Spend256View256Check4Keccac256Value extends AddrValue implements
    Spend256View256Check4Keccac256 {

  public V35Spend256View256Check4Keccac256Value(final byte[] data) {
    super(data);
  }

  @Override
  public AddrFactory<? extends Addr> getAddrFactory() {
    return Version.x35;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other != null && other instanceof Spend256View256Check4Keccac256) {
      final Addr otherAddr = (Addr) other;
      return Arrays.equals(getVersion(), otherAddr.getVersion()) && otherAddr.digestEquals(data);
    }
    return false;
  }
}
