package systems.comodal.hash;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.ByteOrder;
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
  private static final byte[] TEST_DATA_B = "B".getBytes(StandardCharsets.UTF_8);

  private byte[] digest;
  private byte[] digestB;

  private Hash discrete;
  private Hash offset;
  private Hash offset2;
  private Hash reverseOverlay;
  private Hash offsetReverse;

  private Hash discreteB;
  private Hash offsetB;
  private Hash reverseOverlayB;
  private Hash offsetReverseB;

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
    digestB = factory.hashRaw(TEST_DATA_B);

    final byte[] offsetBytes = new byte[digest.length + 7];
    System.arraycopy(digest, 0, offsetBytes, 3, digest.length);
    final byte[] reverse = HashFactory.copyReverse(offsetBytes, 3, digest.length);
    final byte[] reverseOffset = new byte[digest.length * 2];
    System.arraycopy(reverse, 0, reverseOffset, digest.length, digest.length);
    discrete = factory.overlay(digest);
    offset = factory.overlay(offsetBytes, 3);
    reverseOverlay = factory.reverseOverlay(reverse, 0);
    offsetReverse = factory.reverseOverlay(reverseOffset, digest.length);

    final byte[] offsetBytes2 = new byte[digest.length + 61];
    System.arraycopy(digest, 0, offsetBytes2, 17, digest.length);
    offset2 = factory.overlay(offsetBytes2, 17);

    final byte[] offsetBytesB = new byte[digestB.length + 7];
    System.arraycopy(digestB, 0, offsetBytesB, 3, digestB.length);
    final byte[] reverseB = HashFactory.copyReverse(offsetBytesB, 3, digestB.length);
    final byte[] reverseOffsetB = new byte[digestB.length * 2];
    System.arraycopy(reverseB, 0, reverseOffsetB, digestB.length, digestB.length);
    discreteB = factory.overlay(digestB);
    offsetB = factory.overlay(offsetBytesB, 3);
    reverseOverlayB = factory.reverseOverlay(reverseB, 0);
    offsetReverseB = factory.reverseOverlay(reverseOffsetB, digestB.length);
  }

  @Test
  public void testToString() {
    factories.forEach(hashFactory -> assertNotNull(hashFactory.toString()));
  }

  @Test
  public void testEquals() {
    factories.forEach(hashFactory -> {
      assertTrue(discrete.equals(discrete));
      assertTrue(offset.equals(offset));
      assertTrue(reverseOverlay.equals(reverseOverlay));
      assertTrue(offsetReverse.equals(offsetReverse));

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

      assertFalse(discrete.equals(null));
      assertFalse(offset.equals(null));
      assertFalse(reverseOverlay.equals(null));
      assertFalse(offsetReverse.equals(null));

      assertFalse(discrete.equals(""));
      assertFalse(offset.equals(""));
      assertFalse(reverseOverlay.equals(""));
      assertFalse(offsetReverse.equals(""));

      assertNotEquals(discrete, discreteB);
      assertNotEquals(discrete, offsetB);
      assertNotEquals(discrete, reverseOverlayB);
      assertNotEquals(discrete, offsetReverseB);

      assertNotEquals(offset, discreteB);
      assertNotEquals(offset, offsetB);
      assertNotEquals(offset, reverseOverlayB);
      assertNotEquals(offset, offsetReverseB);

      assertNotEquals(reverseOverlay, discreteB);
      assertNotEquals(reverseOverlay, offsetB);
      assertNotEquals(reverseOverlay, reverseOverlayB);
      assertNotEquals(reverseOverlay, offsetReverseB);

      assertNotEquals(offsetReverse, discreteB);
      assertNotEquals(offsetReverse, offsetB);
      assertNotEquals(offsetReverse, reverseOverlayB);
      assertNotEquals(offsetReverse, offsetReverseB);
    });
  }

  @Test
  public void testCompareTo() {
    factories.forEach(hashFactory -> {
      final boolean gt = Arrays.compareUnsigned(digest, digestB) > 0;

      assertEquals(gt, discrete.compareHashTo(discreteB) > 0);
      assertEquals(gt, discrete.compareHashTo(offsetB) > 0);
      assertEquals(gt, discrete.compareHashTo(reverseOverlayB) > 0);
      assertEquals(gt, discrete.compareHashTo(offsetReverseB) > 0);

      assertEquals(gt, offset.compareHashTo(offsetB) > 0);
      assertEquals(gt, offset.compareHashTo(discreteB) > 0);
      assertEquals(gt, offset.compareHashTo(offsetReverseB) > 0);
      assertEquals(gt, offset.compareHashTo(reverseOverlayB) > 0);

      assertEquals(gt, reverseOverlay.compareHashTo(reverseOverlayB) > 0);
      assertEquals(gt, reverseOverlay.compareHashTo(discreteB) > 0);
      assertEquals(gt, reverseOverlay.compareHashTo(offsetB) > 0);
      assertEquals(gt, reverseOverlay.compareHashTo(offsetReverseB) > 0);

      assertEquals(gt, offsetReverse.compareHashTo(offsetReverseB) > 0);
      assertEquals(gt, offsetReverse.compareHashTo(discreteB) > 0);
      assertEquals(gt, offsetReverse.compareHashTo(offsetB) > 0);
      assertEquals(gt, offsetReverse.compareHashTo(reverseOverlayB) > 0);

      assertEquals(0, discrete.compareHashTo(discrete));
      assertEquals(0, offset.compareHashTo(offset));
      assertEquals(0, reverseOverlay.compareHashTo(reverseOverlay));
      assertEquals(0, offsetReverse.compareHashTo(offsetReverse));

      assertEquals(0, discrete.compareHashTo(offset));
      assertEquals(0, discrete.compareHashTo(reverseOverlay));
      assertEquals(0, discrete.compareHashTo(offsetReverse));

      assertEquals(0, offset.compareHashTo(discrete));
      assertEquals(0, offset.compareHashTo(offsetReverse));
      assertEquals(0, offset.compareHashTo(reverseOverlay));

      assertEquals(0, reverseOverlay.compareHashTo(discrete));
      assertEquals(0, reverseOverlay.compareHashTo(offset));
      assertEquals(0, reverseOverlay.compareHashTo(offsetReverse));

      assertEquals(0, offsetReverse.compareHashTo(discrete));
      assertEquals(0, offsetReverse.compareHashTo(offset));
      assertEquals(0, offsetReverse.compareHashTo(reverseOverlay));
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
      assertEquals(discrete.digestToBigInteger(), offset.digestToBigInteger());
      assertEquals(offset.digestToBigInteger(), offset2.digestToBigInteger());
      assertEquals(offset2.digestToBigInteger(), reverseOverlay.digestToBigInteger());
      assertEquals(reverseOverlay.digestToBigInteger(), offsetReverse.digestToBigInteger());
    });
  }

  @Test
  public void testCopy() {
    factories.forEach(hashFactory -> {
      assertArrayEquals(digest, discrete.copyDigest());
      assertArrayEquals(digest, offset.copyDigest());
      assertArrayEquals(digest, offset2.copyDigest());
      assertArrayEquals(digest, reverseOverlay.copyDigest());
      assertArrayEquals(digest, offsetReverse.copyDigest());
    });
  }

  @Test
  public void testCopyTo() {
    final int testOffset = 3;
    factories.forEach(hashFactory -> {
      final byte[] expected = new byte[digest.length << 1];
      final byte[] test = new byte[expected.length];
      System.arraycopy(digest, 0, expected, testOffset, digest.length);

      discrete.copyDigestTo(test, testOffset);
      assertArrayEquals(expected, test);
      offset.copyDigestTo(test, testOffset);
      assertArrayEquals(expected, test);
      offset2.copyDigestTo(test, testOffset);
      assertArrayEquals(expected, test);
      reverseOverlay.copyDigestTo(test, testOffset);
      assertArrayEquals(expected, test);
      offsetReverse.copyDigestTo(test, testOffset);
      assertArrayEquals(expected, test);
    });
  }

  @Test
  public void testCopyReverse() {
    factories.forEach(hashFactory -> {
      final byte[] expected = HashFactory.copyReverse(digest);
      assertArrayEquals(expected, discrete.copyDigestReverse());
      assertArrayEquals(expected, offset.copyDigestReverse());
      assertArrayEquals(expected, offset2.copyDigestReverse());
      assertArrayEquals(expected, reverseOverlay.copyDigestReverse());
      assertArrayEquals(expected, offsetReverse.copyDigestReverse());
    });
  }

  @Test
  public void testCopyToReverse() {
    final int testOffset = 3;
    factories.forEach(hashFactory -> {
      final byte[] expected = new byte[digest.length << 1];
      final byte[] test = new byte[expected.length];
      byte[] reverseDigest = HashFactory.copyReverse(digest);
      System.arraycopy(reverseDigest, 0, expected,
          expected.length - (digest.length + testOffset - 1), digest.length);

      discrete.copyDigestToReverse(test, expected.length - testOffset);
      assertArrayEquals(expected, test);
      offset.copyDigestToReverse(test, expected.length - testOffset);
      assertArrayEquals(expected, test);
      offset2.copyDigestToReverse(test, expected.length - testOffset);
      assertArrayEquals(expected, test);
      reverseOverlay.copyDigestToReverse(test, expected.length - testOffset);
      assertArrayEquals(expected, test);
      offsetReverse.copyDigestToReverse(test, expected.length - testOffset);
      assertArrayEquals(expected, test);
    });
  }

  @Test
  public void testUpdateReverse() {
    factories.forEach(hashFactory -> {
      final MessageDigest msgDigest = hashFactory.getMessageDigest();
      final byte[] expected = msgDigest.digest(HashFactory.copyReverse(digest));
      discrete.updateDigestReverse(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offset.updateDigestReverse(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offset2.updateDigestReverse(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      reverseOverlay.updateDigestReverse(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offsetReverse.updateDigestReverse(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
    });
  }

  @Test
  public void testUpdate() {
    factories.forEach(hashFactory -> {
      final MessageDigest msgDigest = hashFactory.getMessageDigest();
      final byte[] expected = msgDigest.digest(digest);
      discrete.updateDigest(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offset.updateDigest(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offset2.updateDigest(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      reverseOverlay.updateDigest(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
      offsetReverse.updateDigest(msgDigest);
      assertArrayEquals(expected, msgDigest.digest());
    });
  }

  @Test
  public void testByteOrder() {
    factories.forEach(hashFactory -> {
      final byte[] reverseDigest = HashFactory.copyReverse(digest);
      testByteOrder(digest, reverseDigest, discrete);
      testByteOrder(digest, reverseDigest, offset);
      testByteOrder(digest, reverseDigest, offset2);
      testByteOrder(digest, reverseDigest, reverseOverlay);
      testByteOrder(digest, reverseDigest, offsetReverse);
    });
  }

  private void testByteOrder(final byte[] expected, final byte[] reverseExpected,
      final Hash hash) {
    final byte[] backingArray = hash.getBackingDigestData();
    if (hash.getDigestByteOrder() == ByteOrder.LITTLE_ENDIAN) {
      Arrays.equals(expected, 0, expected.length,
          backingArray, hash.getDigestOffset(), hash.getDigestOffset() + hash.getDigestLength());
    } else {
      Arrays.equals(reverseExpected, 0, expected.length,
          backingArray, hash.getDigestOffset() - (hash.getDigestLength() - 1), hash.getDigestOffset() + 1);
    }
  }

  @Test
  public void testGetDiscrete() {
    factories.forEach(hashFactory -> {
      assertTrue(discrete == discrete.getDiscreteHash());
      assertEquals(discrete, discrete.getDiscreteHash());
      assertTrue(digest == discrete.getDiscreteDigest());

      assertFalse(offset == offset.getDiscreteHash());
      assertEquals(offset, offset.getDiscreteHash());
      assertFalse(digest == offset.getDiscreteDigest());
      assertArrayEquals(digest, offset.getDiscreteDigest());

      assertFalse(offset2 == offset2.getDiscreteHash());
      assertEquals(offset2, offset2.getDiscreteHash());
      assertFalse(digest == offset2.getDiscreteDigest());
      assertArrayEquals(digest, offset2.getDiscreteDigest());

      assertFalse(reverseOverlay == reverseOverlay.getDiscreteHash());
      assertEquals(reverseOverlay, reverseOverlay.getDiscreteHash());
      assertFalse(digest == reverseOverlay.getDiscreteDigest());
      assertArrayEquals(digest, reverseOverlay.getDiscreteDigest());

      assertFalse(offsetReverse == offsetReverse.getDiscreteHash());
      assertEquals(offsetReverse, offsetReverse.getDiscreteHash());
      assertFalse(digest == offsetReverse.getDiscreteDigest());
      assertArrayEquals(digest, offsetReverse.getDiscreteDigest());
    });
  }
}
