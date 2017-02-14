package systems.comodal.hash.api;

import java.math.BigInteger;
import java.security.MessageDigest;

public interface Hash extends Comparable<Hash> {

  byte[] getBackingData();

  int getOffset();
  
  static void reverse(final byte[] bytes) {
    byte tmp;
    for (int i = 0, j = bytes.length - 1; j > i; i++, j--) {
      tmp = bytes[j];
      bytes[j] = bytes[i];
      bytes[i] = tmp;
    }
  }

  static byte[] copyReverse(final byte[] data, int offset, final int len) {
    offset += len;
    final byte[] bytes = new byte[len];
    for (int i = 0; i < len; ) {
      bytes[i++] = data[--offset];
    }
    return bytes;
  }

  HashFactory<? extends Hash> getFactory();

  default int getDigestLength() {
    return getFactory().getDigestLength();
  }

  long applyToLong(final ByteToLongOperator rawOperator);

  long applyReverseToLong(final ByteToLongOperator rawOperator);

  int applyToInt(final ByteToIntOperator rawOperator);

  int applyReverseToInt(final ByteToIntOperator rawOperator);

  BigInteger toBigInteger();

  void copyHashTo(final byte[] to, final int offset);

  void copyHashToVolatile(final byte[] to, final int offset);

  /**
   * If the current Hash is already discrete this method should return itself.
   *
   * @return A Hash instance backed by a dedicated byte array with a digestLength exactly the same
   * as the number of bytes that constitute the digest.
   */
  default Hash getDiscrete() {
    return getFactory().overlay(getDigest());
  }

  void update(final MessageDigest messageDigest);

  default byte[] copyToReverse() {
    final byte[] reverseHash = new byte[32];
    copyToReverse(reverseHash, 31);
    return reverseHash;
  }

  void copyToReverse(final byte[] to, final int offset);

  boolean equals(final byte[] data, int offset);

  boolean equals(final byte[] data);

  boolean equalsReverse(final byte[] data, int offset);

  int compareTo(final byte[] other, int offset);

  int compareTo(final byte[] other);

  int compareToReverse(final byte[] other, int offset);

  byte[] getDigest();

  @FunctionalInterface
  interface ByteToLongOperator {

    long apply(final byte[] data, int offset, final int next);
  }

  @FunctionalInterface
  interface ByteToIntOperator {

    int apply(final byte[] data, int offset, final int next);
  }
}
