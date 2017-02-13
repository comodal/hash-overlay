package systems.comodal.hash;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.security.MessageDigest;

public interface HashFactory<H extends Hash> extends BaseFactory<H> {

  VarHandle BA = MethodHandles.arrayElementVarHandle(byte[].class);

  static byte[] hashRaw(final MessageDigest messageDigest, final byte[] data) {
    return messageDigest.digest(data);
  }

  static byte[] hashRaw(final MessageDigest messageDigest, final byte[] data, final int offset,
      final int len) {
    messageDigest.update(data, offset, len);
    return messageDigest.digest();
  }

  static byte[] hashTwiceReverseRaw(final MessageDigest messageDigest, final byte[] data) {
    messageDigest.update(data);
    final byte[] hash = messageDigest.digest(messageDigest.digest());
    Hash.reverse(hash);
    return hash;
  }

  static byte[] hashTwiceRaw(final MessageDigest messageDigest, final byte[] data) {
    messageDigest.update(data);
    return messageDigest.digest(messageDigest.digest());
  }

  static byte[] hashTwiceRaw(final MessageDigest messageDigest, final byte[] data, final int offset,
      final int len) {
    messageDigest.update(data, offset, len);
    return messageDigest.digest(messageDigest.digest());
  }

  static byte[] hashTwiceRaw(final MessageDigest messageDigest, final byte prefix,
      final byte[] data) {
    messageDigest.update(prefix);
    messageDigest.update(data);
    return messageDigest.digest(messageDigest.digest());
  }

  MessageDigest getMessageDigest();

  int getDigestLength();

  default byte[] hashTwiceRaw(final byte[] data) {
    return hashTwiceRaw(getMessageDigest(), data);
  }

  default byte[] hashTwiceRaw(final byte[] data, final int offset, final int len) {
    return hashTwiceRaw(getMessageDigest(), data, offset, len);
  }

  default byte[] hashTwiceRaw(final byte prefix, final byte[] data) {
    return hashTwiceRaw(getMessageDigest(), prefix, data);
  }

  default byte[] hashRaw(final byte[] data) {
    return hashRaw(getMessageDigest(), data);
  }

  default byte[] hashRaw(final byte[] data, final int offset, final int len) {
    return hashRaw(getMessageDigest(), data, offset, len);
  }

  default byte[] hashTwiceReverseRaw(final byte[] data) {
    return hashTwiceReverseRaw(getMessageDigest(), data);
  }

  default H hash(final byte[] data) {
    return overlay(hashRaw(data));
  }

  default H hashTwice(final byte[] data) {
    return overlay(hashTwiceRaw(data));
  }

  default H hashTwiceReverse(final byte[] data) {
    return reverseOverlay(hashTwiceRaw(data));
  }

  default H hashTwiceReverse(final byte[] data, final int offset, final int len) {
    return reverseOverlay(hashTwiceRaw(data, offset, len));
  }

  default H hashReverse(final byte[] data) {
    return reverseOverlay(hashRaw(data));
  }

  default H reverseOverlay(final byte[] digest) {
    Hash.reverse(digest);
    return overlay(digest);
  }

  default H discreteVolatile(final byte[] digest, int offset) {
    final byte[] hash = new byte[getDigestLength()];
    for (int i = 0; i < hash.length; ) {
      digest[i++] = (byte) BA.getVolatile(digest, offset++);
    }
    return overlay(hash);
  }

  /**
   * Creates a copy of the supplied byte array to serve as the backing Hash data.
   *
   * @return A new Hash instance backed by a copy of the supplied byte array.
   */
  default H copy(final byte[] digest, final int offset) {
    final byte[] discrete = new byte[getDigestLength()];
    System.arraycopy(digest, offset, discrete, 0, discrete.length);
    return overlay(discrete);
  }
}
