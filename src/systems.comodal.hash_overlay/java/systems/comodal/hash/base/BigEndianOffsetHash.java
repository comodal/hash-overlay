package systems.comodal.hash.base;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.Arrays;
import systems.comodal.hash.api.Hash;

public abstract class BigEndianOffsetHash implements Hash {

  protected final byte[] data;
  protected final int offset;

  protected BigEndianOffsetHash(final byte[] data, final int offset) {
    this.data = data;
    this.offset = offset;
  }

  @Override
  public int hashCode() {
    int offset = this.offset + getDigestLength();
    return (data[--offset] & 0xFF)
        | (data[--offset] & 0xFF) << 8
        | (data[--offset] & 0xFF) << 16
        | (data[--offset] & 0xFF) << 24;
  }

  @Override
  public byte[] getBackingData() {
    return data;
  }

  @Override
  public int getOffset() {
    return offset;
  }

  private int getOffsetLength() {
    return getFactory().getOffsetLength();
  }

  @Override
  public ByteOrder getByteOrder() {
    return ByteOrder.BIG_ENDIAN;
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
  public BigInteger toBigInteger() {
    return new BigInteger(1, data, offset, getDigestLength());
  }

  @Override
  public byte[] copy() {
    final byte[] copy = new byte[getDigestLength()];
    System.arraycopy(this.data, this.offset, copy, 0, getDigestLength());
    return copy;
  }

  @Override
  public byte[] copyReverse() {
    final byte[] reverseHash = new byte[getDigestLength()];
    copyToReverse(reverseHash, getOffsetLength());
    return reverseHash;
  }

  @Override
  public void copyTo(final byte[] to, final int offset) {
    System.arraycopy(this.data, this.offset, to, offset, getDigestLength());
  }

  @Override
  public void copyToReverse(final byte[] to, int offset) {
    for (int i = this.offset, max = i + getDigestLength(); i < max; --offset, ++i) {
      to[offset] = this.data[i];
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
  public boolean digestEquals(final byte[] other, int offset) {
    for (int i = this.offset, max = this.offset + getDigestLength(); i < max; ++i, ++offset) {
      if (this.data[i] != other[offset]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean digestEquals(final byte[] other) {
    int index = this.offset;
    for (final byte b : other) {
      if (b != this.data[index]) {
        return false;
      }
      ++index;
    }
    return true;
  }

  @Override
  public boolean digestEqualsReverse(final byte[] other, int offset) {
    for (int i = this.offset, max = i + getDigestLength(); i < max; ++i, --offset) {
      if (this.data[i] != other[offset]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int compareTo(final Hash other) {
    return other.compareDigestTo(data, offset);
  }

  @Override
  public int compareDigestTo(final byte[] other, final int offset) {
    return Arrays.compare(other, offset, offset + getDigestLength(), data, this.offset,
        this.offset + getDigestLength());
  }

  @Override
  public int compareDigestTo(final byte[] other) {
    return Arrays
        .compare(other, 0, getDigestLength(), data, this.offset, this.offset + getDigestLength());
  }

  @Override
  public int compareDigestToReverse(final byte[] other, int offset) {
    for (int i = this.offset, max = i + getDigestLength(); i < max; ++i, --offset) {
      if (this.data[i] != other[offset]) {
        return Byte.compare(other[offset], this.data[i]);
      }
    }
    return 0;
  }
}
