package systems.comodal.hash;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

public class HashTest {

  private static final byte[] TEST_DATA = "TEST DATA".getBytes(StandardCharsets.UTF_8);

  private byte[] digest;
  private Hash discrete;
  private Hash offset;
  private Hash offset2;
  private Hash reverseOverlay;
  private Hash offsetReverse;
  private Stream<DigestAlgo> factories;

  @BeforeClass
  public static void beforeClass() {
    Security.addProvider(new BouncyCastleProvider());
  }

  @Before
  public void before() {
    factories = Arrays.stream(DigestAlgo.values()).peek(this::setupTestHashes);
  }

  private void setupTestHashes(final HashFactory<? extends Hash> factory) {
    digest = factory.hashRaw(TEST_DATA);

    final byte[] offsetBytes = new byte[digest.length + 7];
    System.arraycopy(digest, 0, offsetBytes, 3, digest.length);
    final byte[] reverse = Hash.copyReverse(offsetBytes, 3, digest.length);
    final byte[] reverseOffset = new byte[digest.length * 2];
    System.arraycopy(reverse, 0, reverseOffset, digest.length, digest.length);
    discrete = factory.overlay(digest);
    offset = factory.overlay(offsetBytes, 3);
    reverseOverlay = factory.reverseOverlay(reverse, 0);
    offsetReverse = factory.reverseOverlay(reverseOffset, digest.length);

    final byte[] offsetBytes2 = new byte[digest.length + 61];
    System.arraycopy(digest, 0, offsetBytes2, 17, digest.length);
    offset2 = factory.overlay(offsetBytes2, 17);
  }

  @Test
  public void testDigestLength() {
    for (HashFactory<? extends Hash> hashFactory : DigestAlgo.values()) {
      assertEquals(hashFactory.getMessageDigest().getDigestLength(), hashFactory.getDigestLength());
    }
  }

  @Test
  public void testEquals() {
    factories.forEach(hashFactory -> {
      assertEquals(discrete, offset);
      assertEquals(discrete, offset2);
      assertEquals(discrete, reverseOverlay);
      assertEquals(discrete, offsetReverse);

      assertEquals(offset, discrete);
      assertEquals(offset, offset2);
      assertEquals(offset, reverseOverlay);
      assertEquals(offset, offsetReverse);

      assertEquals(reverseOverlay, discrete);
      assertEquals(reverseOverlay, offset);
      assertEquals(reverseOverlay, offset2);
      assertEquals(reverseOverlay, offsetReverse);

      assertEquals(offsetReverse, discrete);
      assertEquals(offsetReverse, offset);
      assertEquals(offsetReverse, offset2);
      assertEquals(offsetReverse, reverseOverlay);
    });
  }

  @Test
  public void testHashcode() {
    factories.forEach(hashFactory -> {
      assertEquals(offset.hashCode(), discrete.hashCode());
      assertEquals(reverseOverlay.hashCode(), offset.hashCode());
      assertEquals(offsetReverse.hashCode(), offset.hashCode());
      assertEquals(offset.hashCode(), offset2.hashCode());
      assertEquals(discrete.hashCode(), reverseOverlay.hashCode());
      assertEquals(discrete.hashCode(), offsetReverse.hashCode());

      final Set<Hash> hashSet = new HashSet<>(1);
      hashSet.add(offset);
      assertTrue(hashSet.contains(reverseOverlay));
      assertFalse(hashSet.add(discrete));
      assertFalse(hashSet.add(offset2));
      assertFalse(hashSet.add(offsetReverse));
    });
  }

  @Test
  public void testToBigInteger() {
    factories.forEach(hashFactory -> {
      assertEquals(discrete.toBigInteger(), offset.toBigInteger());
      assertEquals(offset.toBigInteger(), offset2.toBigInteger());
      assertEquals(offset2.toBigInteger(), reverseOverlay.toBigInteger());
      assertEquals(reverseOverlay.toBigInteger(), offsetReverse.toBigInteger());
    });
  }

  @Test
  public void testCopyReverse() {
    factories.forEach(hashFactory -> {
      final byte[] expected = Hash.copyReverse(digest);
      assertArrayEquals(expected, discrete.copyReverse());
      assertArrayEquals(expected, offset.copyReverse());
      assertArrayEquals(expected, offset2.copyReverse());
      assertArrayEquals(expected, reverseOverlay.copyReverse());
      assertArrayEquals(expected, offsetReverse.copyReverse());
    });
  }

  @Test
  public void testUpdateReverse() {
    factories.forEach(hashFactory -> {
      final MessageDigest msgDigest = hashFactory.getMessageDigest();
      final byte[] expected = msgDigest.digest(Hash.copyReverse(digest));
      discrete.updateReverse(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offset.updateReverse(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offset2.updateReverse(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      reverseOverlay.updateReverse(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offsetReverse.updateReverse(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
    });
  }

  @Test
  public void testUpdate() {
    factories.forEach(hashFactory -> {
      final MessageDigest msgDigest = hashFactory.getMessageDigest();
      final byte[] expected = msgDigest.digest(digest);
      discrete.update(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offset.update(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offset2.update(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      reverseOverlay.update(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offsetReverse.update(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
    });
  }

  @Test
  public void testCompareTo() {
    factories.forEach(hashFactory -> {
      assertEquals(0, reverseOverlay.compareTo(offset));
    });
  }
}
