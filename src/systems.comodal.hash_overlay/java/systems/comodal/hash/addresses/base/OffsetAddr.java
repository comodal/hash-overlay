package systems.comodal.hash.addresses.base;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.Arrays;
import systems.comodal.hash.addresses.api.Addr;

public abstract class OffsetAddr implements Addr {

  protected final byte[] data;
  protected final int offset;

  protected OffsetAddr(final byte[] data, final int offset) {
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
    return getAddrFactory().getOffsetLength();
  }

  @Override
  public ByteOrder getByteOrder() {
    return ByteOrder.BIG_ENDIAN;
  }

  @Override
  public int getDigestLength() {
    return getAddrFactory().getDigestLength();
  }

  @Override
  public Addr getDiscrete() {
    return getAddrFactory().overlay(copy());
  }

  @Override
  public byte[] getDiscreteRaw() {
    return copy();
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
      if (this.data[i] != other[otherOffset]) {
        return Byte.compareUnsigned(other[otherOffset], data[i]);
      }
    }
    return 0;
  }

  @Override
  public int compareTo(final Addr other) {
    final int versionCompare = Arrays.compareUnsigned(getVersion(), other.getVersion());
    return versionCompare == 0 ? other.compareDigestTo(data, offset) : 0;
  }
}
