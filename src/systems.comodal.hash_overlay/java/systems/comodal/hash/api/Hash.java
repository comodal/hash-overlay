package systems.comodal.hash.api;

import java.math.BigInteger;
import java.security.MessageDigest;

public interface Hash {

  static void reverse(final byte[] bytes) {
    byte tmp;
    for (int i = 0, j = bytes.length - 1; j > i; i++, j--) {
      tmp = bytes[j];
      bytes[j] = bytes[i];
      bytes[i] = tmp;
    }
  }

  static byte[] copyReverse(final byte[] data) {
    return copyReverse(data, 0, data.length);
  }

  static byte[] copyReverse(final byte[] data, int offset, final int len) {
    offset += len;
    final byte[] bytes = new byte[len];
    for (int i = 0; i < len; ) {
      bytes[i++] = data[--offset];
    }
    return bytes;
  }

  byte[] getBackingData();

  int getOffset();

  HashFactory<? extends Hash> getFactory();

  int getDigestLength();

  byte[] copy();

  byte[] copyReverse();

  void copyTo(final byte[] to, final int offset);

  void copyToReverse(final byte[] to, final int offset);

  /**
   * If the current Hash is already discrete this method should return itself.
   *
   * @return A Hash instance backed by a dedicated byte array with a digestLength exactly the same
   * as the number of bytes that constitute the digest.
   */
  Hash getDiscrete();

  /**
   * If the current Hash is already discrete this method should return the backing array.
   *
   * @return A byte array with a digestLength exactly the same
   * as the number of bytes that constitute the digest.
   */
  byte[] getDiscreteRaw();

  BigInteger toBigInteger();

  void update(final MessageDigest messageDigest);

  void updateReverse(final MessageDigest messageDigest);

  boolean equals(final byte[] data, int offset);

  boolean equals(final byte[] data);

  boolean equalsReverse(final byte[] data, int offset);

  int compareTo(final Hash other);

  int compareTo(final byte[] other, int offset);

  int compareTo(final byte[] other);

  int compareToReverse(final byte[] other, int offset);
}
