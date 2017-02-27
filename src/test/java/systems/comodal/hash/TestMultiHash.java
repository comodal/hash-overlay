package systems.comodal.hash;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.BeforeClass;
import org.junit.Test;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.multihash.MultiHash;

public class TestMultiHash {

  @BeforeClass
  public static void beforeClass() {
    Security.addProvider(new BouncyCastleProvider());
  }

  @Test
  public void testDecodeVarInt() {
    assertEquals(1, MultiHash.decodeVarInt(new byte[]{(byte) 0b00000001}));
    assertEquals(127, MultiHash.decodeVarInt(new byte[]{(byte) 0b01111111}));
    assertEquals(128, MultiHash.decodeVarInt(new byte[]{(byte) 0b10000000, 0b00000001}));
    assertEquals(255, MultiHash.decodeVarInt(new byte[]{(byte) 0b11111111, 0b00000001}));
    assertEquals(300, MultiHash.decodeVarInt(new byte[]{(byte) 0b10101100, 0b00000010}));
    assertEquals(16384,
        MultiHash.decodeVarInt(new byte[]{(byte) 0b10000000, (byte) 0b10000000, 0b00000001}));
  }

  @Test
  public void testEncodeVarInt() {
    final byte[] test = new byte[3];
    MultiHash.encodeVarInt(1, test);
    assertArrayEquals(new byte[]{(byte) 0b00000001, 0, 0}, test);
    MultiHash.encodeVarInt(127, test);
    assertArrayEquals(new byte[]{(byte) 0b01111111, 0, 0}, test);
    MultiHash.encodeVarInt(128, test);
    assertArrayEquals(new byte[]{(byte) 0b10000000, 0b00000001, 0}, test);
    MultiHash.encodeVarInt(255, test);
    assertArrayEquals(new byte[]{(byte) 0b11111111, 0b00000001, 0}, test);
    MultiHash.encodeVarInt(300, test);
    assertArrayEquals(new byte[]{(byte) 0b10101100, 0b00000010, 0}, test);
    MultiHash.encodeVarInt(16384, test);
    assertArrayEquals(new byte[]{(byte) 0b10000000, (byte) 0b10000000, 0b00000001}, test);

    assertArrayEquals(new byte[]{(byte) 0b00000001}, MultiHash.encodeVarInt(1));
    assertArrayEquals(new byte[]{(byte) 0b01111111}, MultiHash.encodeVarInt(127));
    assertArrayEquals(new byte[]{(byte) 0b10000000, 0b00000001}, MultiHash.encodeVarInt(128));
    assertArrayEquals(new byte[]{(byte) 0b11111111, 0b00000001}, MultiHash.encodeVarInt(255));
    assertArrayEquals(new byte[]{(byte) 0b10101100, 0b00000010}, MultiHash.encodeVarInt(300));
    assertArrayEquals(new byte[]{(byte) 0b10000000, (byte) 0b10000000, 0b00000001},
        MultiHash.encodeVarInt(16384));
  }

  @Test
  public void testDecodeHash() {
    final HashFactory<? extends Hash> hashFactory = SHA256.FACTORY;
    final Hash hash = hashFactory.hash("test".getBytes(StandardCharsets.UTF_8));
    final byte[] multiHash = new byte[2 + hash.getDigestLength()];
    multiHash[0] = (byte) hashFactory.getMultiHashFnCode();
    multiHash[1] = (byte) hashFactory.getDigestLength();
    System.arraycopy(hash.getDiscreteRaw(), 0, multiHash, 2, hash.getDigestLength());

    assertEquals(hash, MultiHash.createOverlay(multiHash));
    assertEquals(hash, MultiHash.createCopy(multiHash));


  }

  @Test
  public void testHashFactoryLookup() {
    HashFactory<? extends Hash> hashFactory = MultiHash.getHashFactory(0xB220);
    assertTrue(BLAKE2B256.FACTORY == hashFactory);

    hashFactory = MultiHash.getHashFactory(MultiHash.encodeVarInt(0xB220));
    assertTrue(BLAKE2B256.FACTORY == hashFactory);
  }
}
