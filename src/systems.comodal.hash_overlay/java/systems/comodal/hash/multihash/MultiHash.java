package systems.comodal.hash.multihash;

import static systems.comodal.hash.multihash.HashFactoryFnCodeFactory.fromFnCode;

import java.util.Objects;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

public final class MultiHash {

  private static final int MAX_VAR_INT_BYTES = 9;
  private static final int VAL_BIT_SHIFT = 7;
  private static final int CONTINUE_MASK = 0b10000000;
  private static final long VAL_MASK = 0b01111111;

  private MultiHash() {
  }

  public static long decodeVarInt(final byte[] varIntPrefixed) {
    return decodeVarInt(varIntPrefixed, 0);
  }

  /**
   * Decodes up to 9 byte long variable length encoded integers defined by the multiformats
   * organization on Github: https://github.com/multiformats/unsigned-varint
   *
   * @param varIntPrefixed binary data containing at least an encoded VarInt at the beginning of the
   * array.
   * @return a decoded unsigned 64 bit integer.
   */
  public static long decodeVarInt(final byte[] varIntPrefixed, int offset) {
    Objects.requireNonNull(varIntPrefixed);
    if (varIntPrefixed.length == offset) {
      throw new IllegalStateException("VarInt data must be at least 1 byte long.");
    }
    long val = varIntPrefixed[offset] & VAL_MASK;
    if ((varIntPrefixed[offset] & CONTINUE_MASK) == 0) {
      return val;
    }
    final int max = varIntPrefixed.length - offset > MAX_VAR_INT_BYTES
        ? offset + MAX_VAR_INT_BYTES
        : varIntPrefixed.length;
    for (int shift = VAL_BIT_SHIFT; ++offset < max; shift += VAL_BIT_SHIFT) {
      val |= (varIntPrefixed[offset] & VAL_MASK) << shift;
      if ((varIntPrefixed[offset] & CONTINUE_MASK) == CONTINUE_MASK) {
        continue;
      }
      return val;
    }
    throw new IllegalArgumentException(String.format(
        "Invalid VarInt encoding directs continuation to %d bytes but only %d bytes are available.",
        offset, max));
  }

  public static byte[] encodeVarInt(long val) {
    if (val <= 127) {
      final byte[] out = new byte[1];
      encodeVarInt(val, out, 0);
      return out;
    }
    final long highestBit = Long.highestOneBit(val);
    final int numTrailingZeros = Long.numberOfTrailingZeros(highestBit);
    final int numBytes = (numTrailingZeros >> 3) + 1;
    final int lasByte = (int) (highestBit >> ((numBytes - 1) << 3));
    final byte[] out = new byte[Integer.numberOfLeadingZeros(lasByte << 24) < numBytes
        ? numBytes + 1
        : numBytes];
    encodeVarInt(val, out, 0);
    return out;
  }

  public static void encodeVarInt(long val, final byte[] out) {
    encodeVarInt(val, out, 0);
  }

  public static void encodeVarInt(long val, final byte[] out, int offset) {
    for (; ; ) {
      out[offset] = (byte) (val & VAL_MASK);
      val >>>= VAL_BIT_SHIFT;
      if (val == 0) {
        return;
      }
      out[offset++] |= CONTINUE_MASK;
    }
  }

  public static Hash createCopy(final byte[] multihash) {
    return createCopy(multihash, 0);
  }

  public static Hash createCopy(final byte[] multihash, int offset) {
    Objects.requireNonNull(multihash);
    if (multihash.length == offset) {
      throw new IllegalStateException("VarInt data must be at least 1 byte long.");
    }
    long val = multihash[offset] & VAL_MASK;
    if ((multihash[offset] & CONTINUE_MASK) == 0) {
      return createCopy(fromFnCode(val), multihash, offset + 1);
    }
    final int max = multihash.length - offset > MAX_VAR_INT_BYTES
        ? offset + MAX_VAR_INT_BYTES
        : multihash.length;
    for (int shift = VAL_BIT_SHIFT; ++offset < max; shift += VAL_BIT_SHIFT) {
      val |= (multihash[offset] & VAL_MASK) << shift;
      if ((multihash[offset] & CONTINUE_MASK) == CONTINUE_MASK) {
        continue;
      }
      return createCopy(fromFnCode(val), multihash, offset + 1);
    }
    throw new IllegalArgumentException(String.format(
        "Invalid VarInt encoding directs continuation to %d bytes but only %d bytes are available.",
        offset, max));
  }

  private static Hash createCopy(final HashFactory<? extends Hash> hashFactory,
      final byte[] multihash, int offset) {
    final int max = multihash.length - offset > MAX_VAR_INT_BYTES
        ? offset + MAX_VAR_INT_BYTES
        : multihash.length;
    for (; offset < max; ) {
      if ((multihash[offset++] & CONTINUE_MASK) == CONTINUE_MASK) {
        continue;
      }
      return hashFactory.copy(multihash, offset);
    }
    throw new IllegalArgumentException(String.format(
        "Invalid VarInt encoding directs continuation to %d bytes but only %d bytes are available.",
        offset, max));
  }

  public static Hash createOverlay(final byte[] multihash) {
    return createOverlay(multihash, 0);
  }

  public static Hash createOverlay(final byte[] multihash, int offset) {
    Objects.requireNonNull(multihash);
    if (multihash.length == offset) {
      throw new IllegalStateException("VarInt data must be at least 1 byte long.");
    }
    long val = multihash[offset] & VAL_MASK;
    if ((multihash[offset] & CONTINUE_MASK) == 0) {
      return createOverlay(fromFnCode(val), multihash, offset + 1);
    }
    final int max = multihash.length - offset > MAX_VAR_INT_BYTES
        ? offset + MAX_VAR_INT_BYTES
        : multihash.length;
    for (int shift = VAL_BIT_SHIFT; ++offset < max; shift += VAL_BIT_SHIFT) {
      val |= (multihash[offset] & VAL_MASK) << shift;
      if ((multihash[offset] & CONTINUE_MASK) == CONTINUE_MASK) {
        continue;
      }
      return createOverlay(fromFnCode(val), multihash, offset + 1);
    }
    throw new IllegalArgumentException(String.format(
        "Invalid VarInt encoding directs continuation to %d bytes but only %d bytes are available.",
        offset, max));
  }

  private static Hash createOverlay(final HashFactory<? extends Hash> hashFactory,
      final byte[] multihash, int offset) {
    final int max = multihash.length - offset > MAX_VAR_INT_BYTES
        ? offset + MAX_VAR_INT_BYTES
        : multihash.length;
    for (; offset < max; ) {
      if ((multihash[offset++] & CONTINUE_MASK) == CONTINUE_MASK) {
        continue;
      }
      return hashFactory.overlay(multihash, offset);
    }
    throw new IllegalArgumentException(String.format(
        "Invalid VarInt encoding directs continuation to %d bytes but only %d bytes are available.",
        offset, max));
  }

  public static HashFactory<? extends Hash> getHashFactory(final byte[] fnCodePrefixed) {
    return fromFnCode((MultiHash.decodeVarInt(fnCodePrefixed)));
  }

  public static HashFactory<? extends Hash> getHashFactory(final long fnCode) {
    return fromFnCode(fnCode);
  }
}
