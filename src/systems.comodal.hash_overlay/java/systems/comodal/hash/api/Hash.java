package systems.comodal.hash.api;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.security.MessageDigest;

public interface Hash {

  byte[] getBackingData();

  int getOffset();

  ByteOrder getByteOrder();

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

  boolean digestEquals(final byte[] data);

  boolean digestEquals(final byte[] data, int offset);

  boolean digestEqualsReverse(final byte[] data, int offset);

  int compareTo(final Hash other);

  int compareDigestTo(final byte[] other);

  int compareDigestTo(final byte[] other, int offset);

  int compareDigestToReverse(final byte[] other, int offset);
}
