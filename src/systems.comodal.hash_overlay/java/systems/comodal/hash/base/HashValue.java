package systems.comodal.hash.base;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.Arrays;
import systems.comodal.hash.api.Hash;

public abstract class HashValue implements Hash {

  protected final byte[] data;

  protected HashValue(final byte[] data) {
    this.data = data;
  }

  @Override
  public int hashCode() {
    int index = getDigestLength();
    return (data[--index] & 0xFF)
        | (data[--index] & 0xFF) << 8
        | (data[--index] & 0xFF) << 16
        | (data[--index] & 0xFF) << 24;
  }

  @Override
  public byte[] getBackingDigestData() {
    return data;
  }

  @Override
  public int getDigestOffset() {
    return 0;
  }

  @Override
  public int getDigestOffsetLength() {
    return getHashFactory().getOffsetLength();
  }

  @Override
  public ByteOrder getDigestByteOrder() {
    return ByteOrder.LITTLE_ENDIAN;
  }

  @Override
  public int getDigestLength() {
    return getHashFactory().getDigestLength();
  }

  @Override
  public Hash getDiscreteHash() {
    return this;
  }

  @Override
  public byte[] getDiscreteDigest() {
    return data;
  }

  @Override
  public boolean isDigestDiscrete() {
    return true;
  }

  @Override
  public BigInteger digestToBigInteger() {
    return new BigInteger(1, data);
  }

  @Override
  public byte[] copyDigest() {
    final byte[] copy = new byte[getDigestLength()];
    System.arraycopy(data, 0, copy, 0, getDigestLength());
    return copy;
  }

  @Override
  public byte[] copyDigestReverse() {
    final byte[] reverseHash = new byte[getDigestLength()];
    copyDigestToReverse(reverseHash, getDigestOffsetLength());
    return reverseHash;
  }

  @Override
  public void copyDigestTo(final byte[] to, final int otherOffset) {
    System.arraycopy(data, 0, to, otherOffset, getDigestLength());
  }

  @Override
  public void copyDigestToReverse(final byte[] to, int otherOffset) {
    for (final byte bite : data) {
      to[otherOffset--] = bite;
    }
  }

  @Override
  public void updateDigest(final MessageDigest messageDigest) {
    messageDigest.update(data);
  }

  @Override
  public void updateDigestReverse(final MessageDigest messageDigest) {
    for (int i = getDigestOffsetLength(); i >= 0; --i) {
      messageDigest.update(data[i]);
    }
  }

  @Override
  public boolean digestEquals(final byte[] other, int otherOffset) {
    for (final byte b : data) {
      if (b != other[otherOffset]) {
        return false;
      }
      ++otherOffset;
    }
    return true;
  }

  @Override
  public boolean digestEquals(final byte[] other) {
    int offset = 0;
    for (final byte b : data) {
      if (b != other[offset]) {
        return false;
      }
      ++offset;
    }
    return true;
  }

  @Override
  public boolean digestEqualsReverse(final byte[] other, int otherOffset) {
    for (final byte b : data) {
      if (b != other[otherOffset]) {
        return false;
      }
      --otherOffset;
    }
    return true;
  }

  @Override
  public int compareDigestTo(final byte[] other, int otherOffset) {
    return Arrays
        .compareUnsigned(other, otherOffset, otherOffset + getDigestLength(), data, 0, getDigestLength());
  }

  @Override
  public int compareDigestTo(final byte[] other) {
    return Arrays.compareUnsigned(other, data);
  }

  @Override
  public int compareDigestToReverse(final byte[] other, int otherOffset) {
    for (final byte b : data) {
      if (b != other[otherOffset]) {
        return Byte.compareUnsigned(other[otherOffset], b);
      }
      --otherOffset;
    }
    return 0;
  }

  @Override
  public int compareHashTo(final Hash other) {
    return other.compareDigestTo(data);
  }
}
