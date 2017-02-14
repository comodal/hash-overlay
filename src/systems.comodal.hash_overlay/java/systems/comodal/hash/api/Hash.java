package systems.comodal.hash.api;

import java.math.BigInteger;
import java.security.MessageDigest;

public interface Hash extends Comparable<Hash> {

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

  byte[] getBackingData();

  int getOffset();

  byte[] getDigest();

  HashFactory<? extends Hash> getFactory();

  default int getDigestLength() {
    return getFactory().getDigestLength();
  }

  void copyTo(final byte[] to, final int offset);

  void copyToVolatile(final byte[] to, final int offset);

  default byte[] copyReverse() {
    final byte[] reverseHash = new byte[getDigestLength()];
    copyToReverse(reverseHash, getDigestLength() - 1);
    return reverseHash;
  }

  void copyToReverse(final byte[] to, final int offset);

  /**
   * If the current Hash is already discrete this method should return itself.
   *
   * @return A Hash instance backed by a dedicated byte array with a digestLength exactly the same
   * as the number of bytes that constitute the digest.
   */
  default Hash getDiscrete() {
    return getFactory().overlay(getDigest());
  }

  long applyToLong(final ByteToLongOperator rawOperator);

  long applyReverseToLong(final ByteToLongOperator rawOperator);

  int applyToInt(final ByteToIntOperator rawOperator);

  int applyReverseToInt(final ByteToIntOperator rawOperator);

  BigInteger toBigInteger();

  void update(final MessageDigest messageDigest);

  boolean equals(final byte[] data, int offset);

  boolean equals(final byte[] data);

  boolean equalsReverse(final byte[] data, int offset);

  int compareTo(final byte[] other, int offset);

  int compareTo(final byte[] other);

  int compareToReverse(final byte[] other, int offset);

  @FunctionalInterface
  interface ByteToLongOperator {

    long apply(final byte[] data, int offset, final int next);
  }

  @FunctionalInterface
  interface ByteToIntOperator {

    int apply(final byte[] data, int offset, final int next);
  }
}
