package systems.comodal.hash.addresses;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.security.Security;
import java.util.Arrays;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.BeforeClass;
import org.junit.Test;
import systems.comodal.hash.KECCAK256;
import systems.comodal.hash.addresses.Sha256RipeMd160Check4DoubleSha256.Version;
import systems.comodal.hash.addresses.api.Addr;

public class AddrTest {

  @BeforeClass
  public static void beforeClass() {
    Security.addProvider(new BouncyCastleProvider());
  }

  @Test
  public void testEncodeAddress() {
    final Addr addr = Version.x00.overlay(Hex.decode("4a22c3c4cbb31e4d03b15550636762bda0baf85a"));
    assertArrayEquals(Base58.decode("17kzeh4N8g49GFvdDzSf8PjaPfyoD1MndL"),
        addr.getChecksummedVerDigest());
  }

  @Test
  public void testSpendViewAddress() {
    // http://moneroexamples.github.io/spendkey/
    // https://xmrtests.llcoins.net/addresstests.html
    final byte[] spendView = new byte[64];
    System.arraycopy(Hex.decode("7e9f73449c1b3a9a83c25a641a95c8f74f7ccd309dd3fba5222ce349052af567"),
        0, spendView, 0, 32);
    System.arraycopy(Hex.decode("fad7d278ee9fedf0034da1812115c5a369d6bd67e1b076afbc4aee579a609ce3"),
        0, spendView, 32, 32);
    final Addr addr = Spend256View256Check4Keccac256.Version.x12.create(spendView);
    final byte[] blah = addr.getChecksummedVerDigest();
    final StringBuilder address = new StringBuilder(95);
    for (int i = 0; i < blah.length; i += 8) {
      final int to = i + 8;
      final byte[] chunk = Arrays.copyOfRange(blah, i, to < blah.length ? to : blah.length);
      address.append(Base58.encode(chunk));
    }
    assertEquals(
        "46RRCV68frZSqzBsh9TWf9iNDrYhPkABUUd16zFLoPECJPjd3AtuTyeh9RhSWAqiCLULKGf9SC1UHWPra64ykRiTSg3RPmW",
        address.toString());
  }
}
