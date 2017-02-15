package systems.comodal.hash.api;

import java.security.MessageDigest;

public interface HashFactory<H extends Hash> {

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

  static <H extends Hash> H merkle(final HashFactory<H> hashFactory, final Hash[] hashes) {
    return hashes.length == 1 ? (H) hashes[0]
        : merkle(hashFactory, hashFactory.getMessageDigest(), hashes);
  }

  static <H extends Hash> H merkle(final HashFactory<H> hashFactory,
      final MessageDigest messageDigest, final Hash[] hashes) {
    final byte[][] tree = new byte[(hashes.length + 1) >> 1][];
    int depthOffset = 0;
    int nextDepthOffset = 0;
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
    return hashFactory.reverseOverlay(tree[0]);
  }

  static <H extends Hash> H merkle(final HashFactory<H> hashFactory, final byte[] data, int offset,
      final int numHashes) {
    return numHashes == 1 ? hashFactory.overlay(data, offset)
        : merkle(hashFactory, hashFactory.getMessageDigest(), data, offset, numHashes);
  }

  static <H extends Hash> H merkle(final HashFactory<H> hashFactory,
      final MessageDigest messageDigest, final byte[] data, int offset, final int numHashes) {
    final byte[][] tree = new byte[(numHashes + 1) >> 1][];
    int nextDepthOffset = 0;
    for (final int maxOffset = data.length - hashFactory.getDigestLength(); offset < maxOffset; ) {
      for (int i = offset + hashFactory.getDigestLength() - 1; i >= offset; --i) {
        messageDigest.update(data[i]);
      }
      offset += hashFactory.getDigestLength();
      for (int i = offset + hashFactory.getDigestLength() - 1; i >= offset; --i) {
        messageDigest.update(data[i]);
      }
      offset += hashFactory.getDigestLength();
      tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
    }
    if (offset < data.length) {
      for (int i = offset + hashFactory.getDigestLength() - 1; i >= offset; --i) {
        messageDigest.update(data[i]);
      }
      for (int i = offset + hashFactory.getDigestLength() - 1; i >= offset; --i) {
        messageDigest.update(data[i]);
      }
      tree[nextDepthOffset++] = messageDigest.digest(messageDigest.digest());
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
    return hashFactory.reverseOverlay(tree[0]);
  }

  MessageDigest getMessageDigest();

  int getDigestLength();

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

  default H merkle(final Hash[] hashes) {
    return merkle(this, hashes);
  }

  default H merkle(final MessageDigest messageDigest, final Hash[] hashes) {
    return hashes.length == 1 ? (H) hashes[0] : merkle(this, messageDigest, hashes);
  }

  default H merkle(final byte[] data, int offset, final int numHashes) {
    return merkle(this, data, offset, numHashes);
  }

  default H merkle(final MessageDigest messageDigest, final byte[] data, int offset,
      final int numHashes) {
    return numHashes == 1 ? overlay(data, offset)
        : merkle(this, messageDigest, data, offset, numHashes);
  }
}
