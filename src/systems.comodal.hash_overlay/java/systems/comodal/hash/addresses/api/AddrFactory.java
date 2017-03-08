package systems.comodal.hash.addresses.api;

import static systems.comodal.hash.api.HashFactory.reverse;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

public interface AddrFactory<A extends Addr> {

  int getDigestLength();

  int getOffsetLength();

  int getVerDigestLength();

  byte[] getVersion();

  int getChecksumLength();

  int getChecksummedVerDigestLength();

  HashFactory<? extends Hash> getChecksumHashFactory();

  /**
   * Uses the supplied byte array as the backing Addr digest.  The length of the byte array
   * should be exactly the length of the digest.  Otherwise, use the overlay constructor with
   * an offset of 0;
   *
   * @return A new Addr instance backed by the supplied byte array.
   */
  A overlay(final byte[] digest);

  /**
   * Uses the supplied byte array as the backing Addr digest.  The offset should be the beginning of
   * the digest.
   *
   * @return A new Addr instance backed by the supplied byte array.
   */
  A overlay(final byte[] digest, final int offset);

  /**
   * Uses the supplied byte array as the backing Addr digest.  The offset should be the beginning of
   * the digest and the assumed direction is in reverse (little-endian).
   *
   * @return A new Addr instance backed by the supplied byte array.
   */
  A reverseOverlay(final byte[] digest, final int offset);

  default A reverseOverlay(final byte[] digest) {
    reverse(digest);
    return overlay(digest);
  }

  /**
   * Creates a copyDigest of the supplied byte array to serve as the backing address data.
   *
   * @return A new Addr instance backed by a copyDigest of the supplied byte array.
   */
  default A copy(final byte[] digest, final int offset) {
    final byte[] discrete = new byte[getDigestLength()];
    System.arraycopy(digest, offset, discrete, 0, discrete.length);
    return overlay(discrete);
  }

  default A create(final byte[] data) {
    return create(data, 0, data.length);
  }

  A create(final byte[] data, final int offset, final int len);
}
