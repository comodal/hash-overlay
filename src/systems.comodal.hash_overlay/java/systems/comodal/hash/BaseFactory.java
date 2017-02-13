package systems.comodal.hash;

public interface BaseFactory<H extends Hash> {

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
}
