package systems.comodal.hash.addresses;

import static org.junit.Assert.assertArrayEquals;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import systems.comodal.hash.addresses.Sha256RipeMd160Check4DoubleSha256.Version;
import systems.comodal.hash.addresses.api.Addr;

public class AddrTest {

  @Test
  public void testEncodeAddress() {
    final Addr addr = Version.x00.overlay(Hex.decode("4a22c3c4cbb31e4d03b15550636762bda0baf85a"));
    assertArrayEquals(Base58.decode("17kzeh4N8g49GFvdDzSf8PjaPfyoD1MndL"),
        addr.getChecksummedVerDigest());
  }
}
