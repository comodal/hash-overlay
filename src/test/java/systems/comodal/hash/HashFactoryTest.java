package systems.comodal.hash;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Arrays;
import java.util.stream.Stream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

public class HashFactoryTest {

  private static final byte[] TEST_DATA = "TEST DATA".getBytes(StandardCharsets.UTF_8);

  private byte[] digest;
  private byte[] hashTwice;
  private Stream<DigestAlgo> factories;

  @BeforeClass
  public static void beforeClass() {
    Security.addProvider(new BouncyCastleProvider());
  }

  @Before
  public void before() {
    factories = Arrays.stream(DigestAlgo.values()).peek(this::setup);
  }

  private void setup(final HashFactory<? extends Hash> factory) {
    digest = factory.hashRaw(TEST_DATA);
    hashTwice = factory.hashRaw(digest);
  }

  @Test
  public void testDigestLength() {
    for (HashFactory<? extends Hash> hashFactory : DigestAlgo.values()) {
      assertEquals(hashFactory.getMessageDigest().getDigestLength(), hashFactory.getDigestLength());
    }
  }

  @Test
  public void testCopy() {
    factories.forEach(hashFactory -> {
      final Hash copy = hashFactory.copy(digest, 0);
      assertArrayEquals(digest, copy.getBackingData());
      assertFalse(digest == copy.getBackingData());
      assertTrue(copy.getDiscreteRaw() == copy.getBackingData());
      assertTrue(copy.digestEquals(digest));

      final byte[] offset = new byte[digest.length * 2];
      System.arraycopy(digest, 0, offset, 3, digest.length);
      final Hash offsetHash = hashFactory.copy(offset, 3);
      assertArrayEquals(digest, offsetHash.getBackingData());
      assertFalse(offset == copy.getBackingData());
      assertTrue(copy.getDiscreteRaw() == copy.getBackingData());
      assertTrue(copy.digestEquals(offset, 3));
    });
  }

  @Test
  public void testHashRaw() {
    final byte[] PREFIXED_TEST_DATA = "1TEST DATA".getBytes(StandardCharsets.UTF_8);
    factories.forEach(hashFactory -> {
      assertArrayEquals(digest, hashFactory.hashRaw(TEST_DATA, 0, TEST_DATA.length));
      assertArrayEquals(hashTwice, hashFactory.hashTwiceRaw(TEST_DATA, 0, TEST_DATA.length));
      assertArrayEquals(HashFactory.copyReverse(hashTwice),
          hashFactory.hashTwiceReverseRaw(TEST_DATA));
      assertArrayEquals(HashFactory.copyReverse(hashTwice),
          hashFactory.hashTwiceReverseRaw(TEST_DATA, 0, TEST_DATA.length));
      assertArrayEquals(hashFactory.hashTwiceRaw(PREFIXED_TEST_DATA),
          hashFactory.hashTwiceRaw((byte) '1', TEST_DATA));
    });
  }

  @Test
  public void testHash() {
    factories.forEach(hashFactory -> {
      assertEquals(hashFactory.overlay(digest), hashFactory.hash(TEST_DATA, 0, TEST_DATA.length));
      final Hash reverse = hashFactory.overlay(HashFactory.copyReverse(digest));
      assertEquals(reverse, hashFactory.hashReverse(TEST_DATA));
      assertEquals(reverse, hashFactory.hashReverse(TEST_DATA, 0, TEST_DATA.length));

      assertEquals(hashFactory.overlay(hashTwice), hashFactory.hashTwice(TEST_DATA));
      assertEquals(hashFactory.overlay(hashTwice),
          hashFactory.hashTwice(TEST_DATA, 0, TEST_DATA.length));
      final Hash hashTwiceReverse = hashFactory.overlay(HashFactory.copyReverse(hashTwice));
      assertEquals(hashTwiceReverse, hashFactory.hashTwiceReverse(TEST_DATA));
      assertEquals(hashTwiceReverse, hashFactory.hashTwiceReverse(TEST_DATA, 0, TEST_DATA.length));
    });
  }

  @Test(expected = IllegalStateException.class)
  public void testUnknownAlgo() {
    HashFactory.getMessageDigestUnchecked("doesntexist");
  }
}
