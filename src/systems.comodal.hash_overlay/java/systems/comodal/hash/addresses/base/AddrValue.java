package systems.comodal.hash.addresses.base;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.Arrays;
import systems.comodal.hash.addresses.api.Addr;

public abstract class AddrValue implements Addr {

  protected final byte[] data;

  protected AddrValue(final byte[] data) {
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
    return getAddrFactory().getOffsetLength();
  }

  @Override
  public ByteOrder getByteOrder() {
    return ByteOrder.LITTLE_ENDIAN;
  }

  @Override
  public int getDigestLength() {
    return getAddrFactory().getDigestLength();
  }

  @Override
  public Addr getDiscrete() {
    return this;
  }

  @Override
  public byte[] getDiscreteRaw() {
    return data;
  }

  @Override
  public byte[] copy() {
    final byte[] copy = new byte[getDigestLength()];
    System.arraycopy(data, 0, copy, 0, getDigestLength());
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
    System.arraycopy(data, 0, to, otherOffset, getDigestLength());
  }

  @Override
  public void copyToReverse(final byte[] to, int otherOffset) {
    for (final byte bite : data) {
      to[otherOffset--] = bite;
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
        .compareUnsigned(other, otherOffset, otherOffset + getDigestLength(), data, 0,
            getDigestLength());
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
  public int compareTo(final Addr other) {
    final int versionCompare = Arrays.compareUnsigned(getVersion(), other.getVersion());
    return versionCompare == 0 ? other.compareDigestTo(data) : 0;
  }
}
