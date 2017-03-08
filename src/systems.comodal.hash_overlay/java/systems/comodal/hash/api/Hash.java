package systems.comodal.hash.api;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.security.MessageDigest;

public interface Hash {

  byte[] getBackingDigestData();

  int getDigestOffset();

  ByteOrder getDigestByteOrder();

  HashFactory<? extends Hash> getHashFactory();

  int getDigestLength();

  /**
   * Useful for jumping to the opposite end of the hash in the backing byte array.
   *
   * @return getDigestLength() - 1
   */
  int getDigestOffsetLength();

  byte[] copyDigest();

  byte[] copyDigestReverse();

  void copyDigestTo(final byte[] to, final int offset);

  void copyDigestToReverse(final byte[] to, final int offset);

  /**
   * If the current Hash is already discrete this method should return itself.
   *
   * @return A Hash instance backed by a dedicated byte array with a digestLength exactly the same
   * as the number of bytes that constitute the digest.
   */
  Hash getDiscreteHash();

  boolean isDigestDiscrete();

  /**
   * If the current Hash is already discrete this method should return the backing array.
   *
   * @return A byte array with a digestLength exactly the same as the number of bytes that
   * constitute the digest.
   */
  byte[] getDiscreteDigest();

  BigInteger digestToBigInteger();

  void updateDigest(final MessageDigest messageDigest);

  void updateDigestReverse(final MessageDigest messageDigest);

  boolean digestEquals(final byte[] data);

  boolean digestEquals(final byte[] data, int offset);

  boolean digestEqualsReverse(final byte[] data, int offset);

  int compareHashTo(final Hash other);

  int compareDigestTo(final byte[] other);

  int compareDigestTo(final byte[] other, int offset);

  int compareDigestToReverse(final byte[] other, int offset);
}
