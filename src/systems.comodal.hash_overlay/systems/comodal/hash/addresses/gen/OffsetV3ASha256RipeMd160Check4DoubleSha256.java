package systems.comodal.hash.addresses.gen;

import java.util.Arrays;
import systems.comodal.hash.addresses.Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.api.Addr;
import systems.comodal.hash.addresses.api.AddrFactory;
import systems.comodal.hash.addresses.base.OffsetAddr;

public final class OffsetV3ASha256RipeMd160Check4DoubleSha256 extends OffsetAddr implements
    Sha256RipeMd160Check4DoubleSha256 {

  public OffsetV3ASha256RipeMd160Check4DoubleSha256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public AddrFactory<? extends Addr> getAddrFactory() {
    return Version.x3A;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other != null && other instanceof Sha256RipeMd160Check4DoubleSha256) {
      final Addr otherAddr = (Addr) other;
      return Arrays.equals(getVersion(), otherAddr.getVersion())
          && otherAddr.digestEquals(data, offset);
    }
    return false;
  }
}
