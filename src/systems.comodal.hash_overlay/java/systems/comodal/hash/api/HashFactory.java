package systems.comodal.hash.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public interface HashFactory<H extends Hash> {

  static MessageDigest getMessageDigestUnchecked(final String algorithm) {
    try {
      return MessageDigest.getInstance(algorithm);
    } catch (final NoSuchAlgorithmException ex) {
      throw new IllegalStateException(ex.getMessage());
    }
  }

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
    reverse(hash);
    return hash;
  }

  static byte[] hashTwiceReverseRaw(final MessageDigest messageDigest, final byte[] data, final int offset,
      final int len) {
    messageDigest.update(data, offset, len);
    final byte[] hash = messageDigest.digest(messageDigest.digest());
    reverse(hash);
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

  static byte[] merkleHashTwice(final MessageDigest messageDigest, final Hash[] hashes,
      final boolean reverseByteOrder) {
    if (hashes.length == 1) {
      return reverseByteOrder ? hashes[0].copyReverse() : hashes[0].getDiscreteRaw();
    }
    final byte[][] tree = new byte[(hashes.length + 1) >> 1][];
    int depthOffset = 0;
    int nextDepthOffset = 0;
    if (reverseByteOrder) {
      for (final int maxOffset = hashes.length - 1; depthOffset < maxOffset; ) {
        hashes[depthOffset++].updateReverse(messageDigest);
        hashes[depthOffset++].updateReverse(messageDigest);
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
      if (depthOffset < hashes.length) {
        hashes[depthOffset].updateReverse(messageDigest);
        hashes[depthOffset].updateReverse(messageDigest);
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
    } else {
      for (final int maxOffset = hashes.length - 1; depthOffset < maxOffset; ) {
        hashes[depthOffset++].update(messageDigest);
        hashes[depthOffset++].update(messageDigest);
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
      if (depthOffset < hashes.length) {
        hashes[depthOffset].update(messageDigest);
        hashes[depthOffset].update(messageDigest);
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
    }
    for (int depthHashes = nextDepthOffset; depthHashes > 1; ) {
      depthOffset = 0;
      nextDepthOffset = 0;
      for (final int maxOffset = depthHashes - 1; depthOffset < maxOffset; ) {
        messageDigest.update(tree[depthOffset++]);
        messageDigest.update(tree[depthOffset++]);
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
      if (depthOffset < depthHashes) {
        final byte[] hash = tree[depthOffset];
        messageDigest.update(hash);
        messageDigest.update(hash);
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
      depthHashes = nextDepthOffset;
    }
    return tree[0];
  }

  static byte[] merkleHashTwice(final HashFactory<? extends Hash> hashFactory,
      final MessageDigest messageDigest, final byte[] data, int offset, final int numHashes,
      final boolean reverseByteOrder) {
    if (numHashes == 1) {
      return reverseByteOrder ? HashFactory.copyReverse(data, offset, hashFactory.getDigestLength())
          : Arrays.copyOfRange(data, offset, hashFactory.getDigestLength());
    }
    final byte[][] tree = new byte[(numHashes + 1) >> 1][];
    int nextDepthOffset = 0;
    if (reverseByteOrder) {
      for (final int maxOffset = data.length - hashFactory.getDigestLength();
          offset < maxOffset; ) {
        for (int i = offset + hashFactory.getOffsetLength(); i >= offset; --i) {
          messageDigest.update(data[i]);
        }
        offset += hashFactory.getDigestLength();
        for (int i = offset + hashFactory.getOffsetLength(); i >= offset; --i) {
          messageDigest.update(data[i]);
        }
        offset += hashFactory.getDigestLength();
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
      if (offset < data.length) {
        for (int i = offset + hashFactory.getOffsetLength(); i >= offset; --i) {
          messageDigest.update(data[i]);
        }
        for (int i = offset + hashFactory.getOffsetLength(); i >= offset; --i) {
          messageDigest.update(data[i]);
        }
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
    } else {
      final int pairLength = hashFactory.getDigestLength() << 1;
      for (final int maxOffset = data.length - hashFactory.getDigestLength(); offset < maxOffset;
          offset += pairLength) {
        messageDigest.update(data, offset, pairLength);
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
      if (offset < data.length) {
        for (final int maxOffset = data.length - hashFactory.getDigestLength(); offset < maxOffset;
            offset += pairLength) {
          messageDigest.update(data, offset, pairLength);
          tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
        }
      }
    }
    for (int depthHashes = nextDepthOffset; depthHashes > 1; ) {
      offset = 0;
      nextDepthOffset = 0;
      for (final int maxOffset = depthHashes - 1; offset < maxOffset; ) {
        messageDigest.update(tree[offset++]);
        messageDigest.update(tree[offset++]);
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
      if (offset < depthHashes) {
        final byte[] hash = tree[offset];
        messageDigest.update(hash);
        messageDigest.update(hash);
        tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
      }
      depthHashes = nextDepthOffset;
    }
    return tree[0];
  }

  MessageDigest getMessageDigest();

  int getDigestLength();

  default int getOffsetLength() {
    return getDigestLength() - 1;
  }

  /**
   * Uses the supplied byte array as the backing Hash digest.  The digestLength of the byte array
   * should be exactly the digestLength of the digest.  Otherwise, use the overlay constructor with
   * an offset of 0;
   *
   * @return A new Hash instance backed by the supplied byte array.
   */
  H overlay(final byte[] digest);

  /**
   * Uses the supplied byte array as the backing Hash digest.  The offset should be the beginning of
   * the digest digest.
   *
   * @return A new Hash instance backed by the supplied byte array.
   */
  H overlay(final byte[] digest, final int offset);

  /**
   * Uses the supplied byte array as the backing Hash digest.  The offset should be the beginning of
   * the digest digest and the assumed direction is in reverse (little-endian).
   *
   * @return A new Hash instance backed by the supplied byte array.
   */
  H reverseOverlay(final byte[] digest, final int offset);

  default H reverseOverlay(final byte[] digest) {
    reverse(digest);
    return overlay(digest);
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

  default byte[] hashRaw(final byte[] data) {
    return hashRaw(getMessageDigest(), data);
  }

  default byte[] hashRaw(final byte[] data, final int offset, final int len) {
    return hashRaw(getMessageDigest(), data, offset, len);
  }

  default byte[] hashTwiceRaw(final byte[] data) {
    return hashTwiceRaw(getMessageDigest(), data);
  }

  default byte[] hashTwiceRaw(final byte[] data, final int offset, final int len) {
    return hashTwiceRaw(getMessageDigest(), data, offset, len);
  }

  default byte[] hashTwiceRaw(final byte prefix, final byte[] data) {
    return hashTwiceRaw(getMessageDigest(), prefix, data);
  }

  default byte[] hashTwiceReverseRaw(final byte[] data) {
    return hashTwiceReverseRaw(getMessageDigest(), data);
  }

  default byte[] hashTwiceReverseRaw(final byte[] data, final int offset, final int len) {
    return hashTwiceReverseRaw(getMessageDigest(), data, offset, len);
  }

  default H hash(final byte[] data) {
    return overlay(hashRaw(data));
  }

  default H hash(final byte[] data, final int offset, final int len) {
    return overlay(hashRaw(data, offset, len));
  }

  default H hashReverse(final byte[] data) {
    return reverseOverlay(hashRaw(data));
  }

  default H hashReverse(final byte[] data, final int offset, final int len) {
    return reverseOverlay(hashRaw(data, offset, len));
  }

  default H hashTwice(final byte[] data) {
    return overlay(hashTwiceRaw(data));
  }

  default H hashTwice(final byte[] data, final int offset, final int len) {
    return overlay(hashTwiceRaw(data, offset, len));
  }

  default H hashTwiceReverse(final byte[] data) {
    return reverseOverlay(hashTwiceRaw(data));
  }

  default H hashTwiceReverse(final byte[] data, final int offset, final int len) {
    return reverseOverlay(hashTwiceRaw(data, offset, len));
  }

  default byte[] merkleHashTwice(final Hash[] hashes, final boolean reverseByteOrder) {
    return merkleHashTwice(getMessageDigest(), hashes, reverseByteOrder);
  }

  default byte[] merkleHashTwice(final byte[] data, int offset, final int numHashes,
      final boolean reverseByteOrder) {
    return merkleHashTwice(getMessageDigest(), data, offset, numHashes, reverseByteOrder);
  }

  default byte[] merkleHashTwice(final MessageDigest messageDigest, final byte[] data, int offset,
      final int numHashes, final boolean reverseByteOrder) {
    return merkleHashTwice(this, messageDigest, data, offset, numHashes, reverseByteOrder);
  }
}
