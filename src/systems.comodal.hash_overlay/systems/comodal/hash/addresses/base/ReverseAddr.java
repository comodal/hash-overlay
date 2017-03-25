package systems.comodal.hash.addresses.base;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.Arrays;
import systems.comodal.hash.addresses.api.Addr;

public abstract class ReverseAddr implements Addr {

  protected final byte[] data;
  protected final int offset;

  protected ReverseAddr(final byte[] data, final int offset) {
    this.data = data;
    this.offset = offset + getOffsetLength();
  }

  @Override
  public int hashCode() {
    int offset = this.offset - getOffsetLength();
    return (data[offset] & 0xFF)
        | (data[++offset] & 0xFF) << 8
        | (data[++offset] & 0xFF) << 16
        | (data[++offset] & 0xFF) << 24;
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
    for (int i = offset, o = 0; o < getDigestLength(); ++o, --i) {
      copy[o] = data[i];
    }
    return copy;
  }

  @Override
  public byte[] copyReverse() {
    final byte[] reverseHash = new byte[getDigestLength()];
    copyToReverse(reverseHash, getOffsetLength());
    return reverseHash;
  }

  @Override
  public void copyTo(final byte[] to, int otherOffset) {
    for (int i = offset, max = otherOffset + getDigestLength(); otherOffset < max;
        --i, ++otherOffset) {
      to[otherOffset] = data[i];
    }
  }

  @Override
  public void copyToReverse(final byte[] to, int otherOffset) {
    System.arraycopy(data, offset - getOffsetLength(), to, otherOffset - getOffsetLength(),
        getDigestLength());
  }

  @Override
  public void update(final MessageDigest messageDigest) {
    for (int i = offset, min = offset - getDigestLength(); i > min; --i) {
      messageDigest.update(data[i]);
    }
  }

  @Override
  public void updateReverse(final MessageDigest messageDigest) {
    messageDigest.update(data, offset - getOffsetLength(), getDigestLength());
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
  public int compareTo(final Addr other) {
    final int versionCompare = Arrays.compareUnsigned(getVersion(), other.getVersion());
    return versionCompare == 0 ? other.compareDigestToReverse(data, offset) : 0;
  }
}
