package systems.comodal.hash;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashTest {

  @BeforeClass
  public static void beforeClass() {
    Security.addProvider(new BouncyCastleProvider());
  }

  @Test
  public void testTypes() {
    final byte[] data = "TEST DATA".getBytes(StandardCharsets.UTF_8);
    List.of(Sha256.FACTORY, RipeMd160.FACTORY, Sha3_256.FACTORY, Md5.FACTORY, Sha1.FACTORY)
        .forEach(factory -> testDigest(factory, factory.hashRaw(data)));
  }

  private void testDigest(final HashFactory<? extends Hash> factory, final byte[] digest) {
    assertEquals(factory.getDigestLength(), digest.length);

    final byte[] offsetBytes = new byte[digest.length + 7];
    System.arraycopy(digest, 0, offsetBytes, 3, digest.length);

    final byte[] reverse = Hash.copyReverse(offsetBytes, 3, digest.length);
    final Hash discrete = factory.overlay(digest);
    final Hash offset = factory.overlay(offsetBytes, 3);
    final Hash offsetReverse = factory.reverseOverlay(reverse, 0);

    final byte[] offsetBytes2 = new byte[digest.length + 61];
    System.arraycopy(digest, 0, offsetBytes2, 17, digest.length);
    final Hash offset2 = factory.overlay(offsetBytes2, 17);

    assertEquals(discrete, offset);
    assertEquals(offset, offsetReverse);
    assertEquals(offset, offset2);
    assertEquals(offsetReverse, offset);
    assertEquals(offset.hashCode(), discrete.hashCode());
    assertEquals(offsetReverse.hashCode(), offset.hashCode());
    assertEquals(offset.hashCode(), offset2.hashCode());
    assertEquals(discrete.hashCode(), offsetReverse.hashCode());

    final Set<Hash> hashSet = new HashSet<>(1);
    hashSet.add(offset);
    assertTrue(hashSet.contains(offsetReverse));
    assertFalse(hashSet.add(discrete));
    assertFalse(hashSet.add(offset2));

    assertEquals(0, offsetReverse.compareTo(offset));
  }
}
