package systems.comodal.hash.base;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.Arrays;
import systems.comodal.hash.api.Hash;

public abstract class DiscreteHash implements Hash {

  protected final byte[] data;

  protected DiscreteHash(final byte[] data) {
    this.data = data;
  }

  @Override
  public int hashCode() {
    int offset = getDigestLength();
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
    return 0;
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
    return this;
  }

  @Override
  public byte[] getDiscreteRaw() {
    return data;
  }

  @Override
  public BigInteger toBigInteger() {
    return new BigInteger(1, data);
  }


  @Override
  public byte[] copy() {
    return data;
  }

  @Override
  public byte[] copyReverse() {
    final byte[] reverseHash = new byte[getDigestLength()];
    copyToReverse(reverseHash, getOffsetLength());
    return reverseHash;
  }

  @Override
  public void copyTo(final byte[] to, final int offset) {
    System.arraycopy(this.data, 0, to, offset, getDigestLength());
  }

  @Override
  public void copyToReverse(final byte[] to, int offset) {
    for (final byte bite : this.data) {
      to[offset--] = bite;
    }
  }

  @Override
  public void update(final MessageDigest messageDigest) {
    messageDigest.update(data);
  }

  @Override
  public void updateReverse(final MessageDigest messageDigest) {
    for (int i = getOffsetLength(); i >= 0; --i) {
      messageDigest.update(data[i]);
    }
  }

  @Override
  public boolean equals(final byte[] other, int offset) {
    for (final byte b : this.data) {
      if (b != other[offset]) {
        return false;
      }
      ++offset;
    }
    return true;
  }

  @Override
  public boolean equals(final byte[] other) {
    int offset = 0;
    for (final byte b : this.data) {
      if (b != other[offset]) {
        return false;
      }
      ++offset;
    }
    return true;
  }

  @Override
  public boolean equalsReverse(final byte[] other, int offset) {
    for (final byte b : this.data) {
      if (b != other[offset]) {
        return false;
      }
      --offset;
    }
    return true;
  }

  @Override
  public int compareTo(final Hash other) {
    return other.compareTo(data);
  }

  @Override
  public int compareTo(final byte[] other, int offset) {
    return Arrays.compare(other, offset, offset + getDigestLength(), data, 0, getDigestLength());
  }

  @Override
  public int compareTo(final byte[] other) {
    return Arrays.compare(other, data);
  }

  @Override
  public int compareToReverse(final byte[] other, int offset) {
    for (final byte b : this.data) {
      if (b != other[offset]) {
        return Byte.compare(other[offset], b);
      }
      --offset;
    }
    return 0;
  }
}
