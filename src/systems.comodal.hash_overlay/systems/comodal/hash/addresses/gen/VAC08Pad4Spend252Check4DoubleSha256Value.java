package systems.comodal.hash.addresses.gen;

import java.util.Arrays;
import systems.comodal.hash.addresses.Pad4Spend252Check4DoubleSha256;
import systems.comodal.hash.addresses.api.Addr;
import systems.comodal.hash.addresses.api.AddrFactory;
import systems.comodal.hash.addresses.base.AddrValue;

public final class VAC08Pad4Spend252Check4DoubleSha256Value extends AddrValue implements
    Pad4Spend252Check4DoubleSha256 {

  public VAC08Pad4Spend252Check4DoubleSha256Value(final byte[] data) {
    super(data);
  }

  @Override
  public AddrFactory<? extends Addr> getAddrFactory() {
    return Version.xAC08;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other != null && other instanceof Pad4Spend252Check4DoubleSha256) {
      final Addr otherAddr = (Addr) other;
      return Arrays.equals(getVersion(), otherAddr.getVersion()) && otherAddr.digestEquals(data);
    }
    return false;
  }
}
