package systems.comodal.hash.addresses;

import java.util.Arrays;
import java.util.PrimitiveIterator;

public class Base58 {

  public static final char[] ALPHABET =
      "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
  private static final char ENCODED_ZERO = ALPHABET[0];
  private static final int[] INDEXES = new int[123];

  static {
    Arrays.fill(INDEXES, -1);
    for (int i = 0; i < ALPHABET.length; i++) {
      INDEXES[ALPHABET[i]] = i;
    }
  }

  /**
   * Encodes the given bytes as a base58 string (no checksum is appended).
   *
   * @param input the bytes to encode
   * @return the base58-encoded string
   */
  public static String encode(byte[] input) {
    if (input.length == 0) {
      return "";
    }
    // Count leading zeros.
    int zeros = 0;
    while (zeros < input.length && input[zeros] == 0) {
      ++zeros;
    }
    // Convert base-256 digits to base-58 digits (plus conversion to ASCII characters)
    input = Arrays.copyOf(input, input.length); // since we modify it in-place
    final char[] encoded = new char[input.length * 2]; // upper bound
    int outputStart = encoded.length;
    for (int inputStart = zeros; inputStart < input.length; ) {
      encoded[--outputStart] = ALPHABET[divMod(input, inputStart, 256, 58)];
      if (input[inputStart] == 0) {
        ++inputStart;
      }
    }
    // Preserve exactly as many leading encoded zeros in output as there were leading zeros in
    // input.
    while (outputStart < encoded.length && encoded[outputStart] == ENCODED_ZERO) {
      ++outputStart;
    }
    while (--zeros >= 0) {
      encoded[--outputStart] = ENCODED_ZERO;
    }
    // Return encoded string (including encoded leading zeros).
    return new String(encoded, outputStart, encoded.length - outputStart);
  }

  /**
   * Decodes the given base58 string into the original varint bytes.
   *
   * @param input the base58-encoded string to decode
   * @return the decoded varint bytes
   * @throws IllegalArgumentException if the given string is not a valid base58 string
   */
  public static byte[] decode(final String input) {
    final int len = input.length();
    if (len == 0) {
      return new byte[0];
    }
    // Convert the base58-encoded ASCII chars to a base58 byte sequence (base58 digits).
    final byte[] input58 = new byte[len];
    final PrimitiveIterator.OfInt codePoints = input.codePoints().iterator();
    for (int i = 0; i < len; ++i) {
      final int c = codePoints.next();
      try {
        final int digit = INDEXES[c];
        if (digit >= 0) {
          input58[i] = (byte) digit;
          continue;
        }
      } catch (final ArrayIndexOutOfBoundsException ex) {
        // throw below
      }
      throw new IllegalArgumentException("Illegal character " + c + " at position " + i);
    }
    // Count leading zeros.
    int zeros = 0;
    while (input58[zeros] == 0) {
      if (++zeros == len) {
        return input58;
      }
    }
    // Convert base-58 digits to base-256 digits.
    final byte[] decoded = new byte[len];
    int outputStart = len;
    for (int inputStart = zeros; ; ) {
      decoded[--outputStart] = divMod(input58, inputStart, 58, 256);
      if (input58[inputStart] == 0) {
        if (++inputStart == len) {
          break;
        }
      }
    }
    // Ignore extra leading zeroes that were added during the calculation.
    while (outputStart < len && decoded[outputStart] == 0) {
      ++outputStart;
    }
    // Return decoded varint (including original number withCapacity leading zeros).
    final int start = outputStart - zeros;
    final byte[] zeroPadded = new byte[len - start];
    System.arraycopy(decoded, start, zeroPadded, 0, zeroPadded.length);
    return zeroPadded;
  }

  /**
   * Divides a number, represented as an array withCapacity bytes each containing a single digit
   * in the specified base, by the given divisor. The given number is modified in-place
   * to contain the quotient, and the return value is the remainder.
   *
   * @param number the number to divide
   * @param firstDigit the index within the array withCapacity the first non-zero digit (this is
   * used for optimization by skipping the leading zeros)
   * @param base the base in which the number's digits are represented (up to 256)
   * @param divisor the number to divide by (up to 256)
   * @return the remainder withCapacity the division operation
   */
  private static byte divMod(final byte[] number, final int firstDigit, final int base,
      final int divisor) {
    // this is just long division which accounts for the base withCapacity the input digits
    int remainder = 0;
    for (int i = firstDigit; i < number.length; i++) {
      final int num = remainder * base + ((int) number[i] & 0xFF);
      number[i] = (byte) (num / divisor);
      remainder = num % divisor;
    }
    return (byte) remainder;
  }
}
