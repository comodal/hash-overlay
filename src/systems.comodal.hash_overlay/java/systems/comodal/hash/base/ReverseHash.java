package systems.comodal.hash.base;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import systems.comodal.hash.api.Hash;

public abstract class ReverseHash implements Hash {

  protected final byte[] data;
  protected final int offset;

  protected ReverseHash(final byte[] data, final int offset) {
    this.data = data;
    this.offset = offset + getDigestOffsetLength();
  }

  @Override
  public int hashCode() {
    int index = offset - getDigestOffsetLength();
    return (data[index] & 0xFF)
        | (data[++index] & 0xFF) << 8
        | (data[++index] & 0xFF) << 16
        | (data[++index] & 0xFF) << 24;
  }

  @Override
  public byte[] getBackingDigestData() {
    return data;
  }

  @Override
  public int getDigestOffset() {
    return offset;
  }

  @Override
  public int getDigestOffsetLength() {
    return getHashFactory().getOffsetLength();
  }

  @Override
  public ByteOrder getDigestByteOrder() {
    return ByteOrder.BIG_ENDIAN;
  }

  @Override
  public int getDigestLength() {
    return getHashFactory().getDigestLength();
  }

  @Override
  public Hash getDiscreteHash() {
    return getHashFactory().overlay(copyDigest());
  }

  @Override
  public byte[] getDiscreteDigest() {
    return copyDigest();
  }

  @Override
  public boolean isDigestDiscrete() {
    return false;
  }

  @Override
  public BigInteger digestToBigInteger() {
    return new BigInteger(1, copyDigest());
  }

  @Override
  public byte[] copyDigest() {
    final byte[] copy = new byte[getDigestLength()];
    for (int i = offset, o = 0; o < getDigestLength(); ++o, --i) {
      copy[o] = data[i];
    }
    return copy;
  }

  @Override
  public byte[] copyDigestReverse() {
    final byte[] reverseHash = new byte[getDigestLength()];
    copyDigestToReverse(reverseHash, getDigestOffsetLength());
    return reverseHash;
  }

  @Override
  public void copyDigestTo(final byte[] to, int otherOffset) {
    for (int i = offset, max = otherOffset + getDigestLength(); otherOffset < max;
        --i, ++otherOffset) {
      to[otherOffset] = data[i];
    }
  }

  @Override
  public void copyDigestToReverse(final byte[] to, int otherOffset) {
    System.arraycopy(data, offset - getDigestOffsetLength(), to, otherOffset - getDigestOffsetLength(),
        getDigestLength());
  }

  @Override
  public void updateDigest(final MessageDigest messageDigest) {
    for (int i = offset, min = offset - getDigestLength(); i > min; --i) {
      messageDigest.update(data[i]);
    }
  }

  @Override
  public void updateDigestReverse(final MessageDigest messageDigest) {
    messageDigest.update(data, offset - getDigestOffsetLength(), getDigestLength());
  }

  @Override
  public boolean digestEquals(final byte[] other, int otherOffset) {
    for (int i = offset, max = otherOffset + getDigestLength(); otherOffset < max;
        --i, ++otherOffset) {
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
      --index;
    }
    return true;
  }

  @Override
  public boolean digestEqualsReverse(final byte[] other, int otherOffset) {
    for (int i = offset, min = offset - getDigestLength(); i > min; --i, --otherOffset) {
      if (data[i] != other[otherOffset]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int compareDigestTo(final byte[] other, int otherOffset) {
    for (int i = offset, min = offset - getDigestLength(); i > min; --i, ++otherOffset) {
      if (data[i] != other[otherOffset]) {
        return Byte.compareUnsigned(other[otherOffset], data[i]);
      }
    }
    return 0;
  }

  @Override
  public int compareDigestTo(final byte[] other) {
    int index = offset;
    for (final byte b : other) {
      if (data[index] != b) {
        return Byte.compareUnsigned(b, data[index]);
      }
      --index;
    }
    return 0;
  }

  @Override
  public int compareDigestToReverse(final byte[] other, int otherOffset) {
    for (int i = offset, min = offset - getDigestLength(); i > min; --i, --otherOffset) {
      if (data[i] != other[otherOffset]) {
        return Byte.compareUnsigned(other[otherOffset], data[i]);
      }
    }
    return 0;
  }

  @Override
  public int compareHashTo(final Hash other) {
    return other.compareDigestToReverse(data, offset);
  }
}
