package systems.comodal.hash.addresses.gen;

import java.util.Arrays;
import systems.comodal.hash.addresses.Pay256Send256Check4DoubleSha256;
import systems.comodal.hash.addresses.api.Addr;
import systems.comodal.hash.addresses.api.AddrFactory;
import systems.comodal.hash.addresses.base.ReverseAddr;

public final class ReverseV16B6Pay256Send256Check4DoubleSha256 extends ReverseAddr implements
    Pay256Send256Check4DoubleSha256 {

  public ReverseV16B6Pay256Send256Check4DoubleSha256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public AddrFactory<? extends Addr> getAddrFactory() {
    return Version.x16B6;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other != null && other instanceof Pay256Send256Check4DoubleSha256) {
      final Addr otherAddr = (Addr) other;
      return Arrays.equals(getVersion(), otherAddr.getVersion())
          && otherAddr.digestEqualsReverse(data, offset);
    }
    return false;
  }
}
