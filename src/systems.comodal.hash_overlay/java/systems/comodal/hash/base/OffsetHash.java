package systems.comodal.hash.base;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.Arrays;
import systems.comodal.hash.api.Hash;

public abstract class OffsetHash implements Hash {

  protected final byte[] data;
  protected final int offset;

  protected OffsetHash(final byte[] data, final int offset) {
    this.data = data;
    this.offset = offset;
  }

  @Override
  public int hashCode() {
    int index = offset + getDigestLength();
    return (data[--index] & 0xFF)
        | (data[--index] & 0xFF) << 8
        | (data[--index] & 0xFF) << 16
        | (data[--index] & 0xFF) << 24;
  }

  @Override
  public byte[] getBackingData() {
    return data;
  }

  @Override
  public int getOffset() {
    return offset;
  }

  @Override
  public int getOffsetLength() {
    return getFactory().getOffsetLength();
  }

  @Override
  public ByteOrder getByteOrder() {
    return ByteOrder.LITTLE_ENDIAN;
  }

  @Override
  public int getDigestLength() {
    return getFactory().getDigestLength();
  }

  @Override
  public Hash getDiscrete() {
    return getFactory().overlay(copy());
  }

  @Override
  public byte[] getDiscreteRaw() {
    return copy();
  }

  @Override
  public boolean isDiscrete() {
    return false;
  }

  @Override
  public BigInteger toBigInteger() {
    return new BigInteger(1, data, offset, getDigestLength());
  }

  @Override
  public byte[] copy() {
    final byte[] copy = new byte[getDigestLength()];
    System.arraycopy(data, offset, copy, 0, getDigestLength());
    return copy;
  }

  @Override
  public byte[] copyReverse() {
    final byte[] reverseHash = new byte[getDigestLength()];
    copyToReverse(reverseHash, getOffsetLength());
    return reverseHash;
  }

  @Override
  public void copyTo(final byte[] to, final int otherOffset) {
    System.arraycopy(data, offset, to, otherOffset, getDigestLength());
  }

  @Override
  public void copyToReverse(final byte[] to, int otherOffset) {
    for (int i = offset, max = offset + getDigestLength(); i < max; --otherOffset, ++i) {
      to[otherOffset] = data[i];
    }
  }

  @Override
  public void update(final MessageDigest messageDigest) {
    messageDigest.update(data, offset, getDigestLength());
  }

  @Override
  public void updateReverse(final MessageDigest messageDigest) {
    for (int i = offset + getOffsetLength(); i >= offset; --i) {
      messageDigest.update(data[i]);
    }
  }

  @Override
  public boolean digestEquals(final byte[] other, int otherOffset) {
    for (int i = offset, max = offset + getDigestLength(); i < max; ++i, ++otherOffset) {
      if (data[i] != other[otherOffset]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean digestEquals(final byte[] other) {
    int index = offset;
    for (final byte b : other) {
      if (b != data[index]) {
        return false;
      }
      ++index;
    }
    return true;
  }

  @Override
  public boolean digestEqualsReverse(final byte[] other, int otherOffset) {
    for (int i = offset, max = i + getDigestLength(); i < max; ++i, --otherOffset) {
      if (data[i] != other[otherOffset]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int compareDigestTo(final byte[] other, final int otherOffset) {
    return Arrays.compareUnsigned(other, otherOffset, otherOffset + getDigestLength(), data, offset,
        offset + getDigestLength());
  }

  @Override
  public int compareDigestTo(final byte[] other) {
    return Arrays
        .compareUnsigned(other, 0, getDigestLength(), data, offset, offset + getDigestLength());
  }

  @Override
  public int compareDigestToReverse(final byte[] other, int otherOffset) {
    for (int i = offset, max = i + getDigestLength(); i < max; ++i, --otherOffset) {
      if (data[i] != other[otherOffset]) {
        return Byte.compareUnsigned(other[otherOffset], data[i]);
      }
    }
    return 0;
  }

  @Override
  public int compareTo(final Hash other) {
    return other.compareDigestTo(data, offset);
  }
}
