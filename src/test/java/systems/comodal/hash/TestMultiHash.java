package systems.comodal.hash;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;
import org.junit.Test;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.multihash.MultiHash;

public class TestMultiHash {

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
}
