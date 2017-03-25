package systems.comodal.hash.addresses.gen;

import java.util.Arrays;
import systems.comodal.hash.addresses.Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.api.Addr;
import systems.comodal.hash.addresses.api.AddrFactory;
import systems.comodal.hash.addresses.base.AddrValue;

public final class V1CB8Sha256RipeMd160Check4DoubleSha256Value extends AddrValue implements
    Sha256RipeMd160Check4DoubleSha256 {

  public V1CB8Sha256RipeMd160Check4DoubleSha256Value(final byte[] data) {
    super(data);
  }

  @Override
  public AddrFactory<? extends Addr> getAddrFactory() {
    return Version.x1CB8;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other != null && other instanceof Sha256RipeMd160Check4DoubleSha256) {
      final Addr otherAddr = (Addr) other;
      return Arrays.equals(getVersion(), otherAddr.getVersion()) && otherAddr.digestEquals(data);
    }
    return false;
  }
}
