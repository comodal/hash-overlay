package systems.comodal.hash;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Arrays;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.BeforeClass;
import org.junit.Test;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.multihash.MultiHash;

public class MultiHashTest {

  @BeforeClass
  public static void beforeClass() {
    Security.addProvider(new BouncyCastleProvider());
  }

  @Test
  public void testDecodeVarInt() {
    assertEquals(0, MultiHash.decodeVarInt(new byte[]{(byte) 0b00000000}));
    assertEquals(1, MultiHash.decodeVarInt(new byte[]{(byte) 0b00000001}));
    assertEquals(127, MultiHash.decodeVarInt(new byte[]{(byte) 0b01111111}));
    assertEquals(128, MultiHash.decodeVarInt(new byte[]{(byte) 0b10000000, 0b00000001}));
    assertEquals(255, MultiHash.decodeVarInt(new byte[]{(byte) 0b11111111, 0b00000001}));
    assertEquals(300, MultiHash.decodeVarInt(new byte[]{(byte) 0b10101100, 0b00000010}));
    assertEquals(8192, MultiHash.decodeVarInt(new byte[]{(byte) 0b10000000, 0b01000000}));
    assertEquals(16384,
        MultiHash.decodeVarInt(new byte[]{(byte) 0b10000000, (byte) 0b10000000, 0b00000001}));
    assertEquals(32768,
        MultiHash.decodeVarInt(new byte[]{(byte) 0b10000000, (byte) 0b10000000, 0b00000010}));
  }

  @Test
  public void testEncodeVarInt() {
    final byte[] test = new byte[3];
    assertEquals(1, MultiHash.encodeVarInt(0, test));
    assertArrayEquals(new byte[]{0, 0, 0}, test);
    assertEquals(1, MultiHash.encodeVarInt(1, test));
    assertArrayEquals(new byte[]{(byte) 0b00000001, 0, 0}, test);
    assertEquals(1, MultiHash.encodeVarInt(127, test));
    assertArrayEquals(new byte[]{(byte) 0b01111111, 0, 0}, test);
    assertEquals(2, MultiHash.encodeVarInt(128, test));
    assertArrayEquals(new byte[]{(byte) 0b10000000, 0b00000001, 0}, test);
    assertEquals(2, MultiHash.encodeVarInt(255, test));
    assertArrayEquals(new byte[]{(byte) 0b11111111, 0b00000001, 0}, test);
    assertEquals(2, MultiHash.encodeVarInt(300, test));
    assertArrayEquals(new byte[]{(byte) 0b10101100, 0b00000010, 0}, test);
    assertEquals(2, MultiHash.encodeVarInt(8192, test));
    assertArrayEquals(new byte[]{(byte) 0b10000000, 0b01000000, 0}, test);
    assertEquals(3, MultiHash.encodeVarInt(16384, test));
    assertArrayEquals(new byte[]{(byte) 0b10000000, (byte) 0b10000000, 0b00000001}, test);
    assertEquals(3, MultiHash.encodeVarInt(32768, test));
    assertArrayEquals(new byte[]{(byte) 0b10000000, (byte) 0b10000000, 0b00000010}, test);

    assertArrayEquals(new byte[]{(byte) 0b00000000}, MultiHash.encodeVarInt(0));
    assertArrayEquals(new byte[]{(byte) 0b00000001}, MultiHash.encodeVarInt(1));
    assertArrayEquals(new byte[]{(byte) 0b01111111}, MultiHash.encodeVarInt(127));
    assertArrayEquals(new byte[]{(byte) 0b10000000, 0b00000001}, MultiHash.encodeVarInt(128));
    assertArrayEquals(new byte[]{(byte) 0b11111111, 0b00000001}, MultiHash.encodeVarInt(255));
    assertArrayEquals(new byte[]{(byte) 0b10101100, 0b00000010}, MultiHash.encodeVarInt(300));
    assertArrayEquals(new byte[]{(byte) 0b10000000, 0b01000000}, MultiHash.encodeVarInt(8192));
    assertArrayEquals(new byte[]{(byte) 0b10000000, (byte) 0b10000000, 0b00000001},
        MultiHash.encodeVarInt(16384));
    assertArrayEquals(new byte[]{(byte) 0b10000000, (byte) 0b10000000, 0b00000010},
        MultiHash.encodeVarInt(32768));
  }

  @Test
  public void testEncodeDecodeVarInt() {
    for (long val = 1; val > 0; val <<= 1) {
      final byte[] varInt = MultiHash.encodeVarInt(val);
      assertEquals(val, MultiHash.decodeVarInt(varInt));
      assertTrue(varInt[varInt.length - 1] > 0);
    }
  }

  @Test
  public void testDecodeHash() {
    final byte[] msg = "test".getBytes(StandardCharsets.UTF_8);
    Arrays.stream(DigestAlgo.values())
        .filter(hashFactory -> hashFactory.getMultiHashFnCode() > 0)
        .forEach(hashFactory -> {
          final Hash hash = hashFactory.hash(msg);
          final byte[] prefix = hash.getFactory().getMultiHashPrefix();
          final byte[] multiHash = new byte[prefix.length + hash.getDigestLength()];
          System.arraycopy(prefix, 0, multiHash, 0, prefix.length);
          hash.copyTo(multiHash, prefix.length);

          assertEquals(hash, MultiHash.createOverlay(multiHash));
          assertEquals(hash, MultiHash.createCopy(multiHash));
        });
  }

  @Test
  public void testHashFactoryLookup() {
    HashFactory<? extends Hash> hashFactory = MultiHash.getHashFactory(0xB220);
    assertTrue(BLAKE2B256.FACTORY == hashFactory);

    hashFactory = MultiHash.getHashFactory(MultiHash.encodeVarInt(0xB220));
    assertTrue(BLAKE2B256.FACTORY == hashFactory);
  }
}
