package systems.comodal.hash.base;

import static systems.comodal.hash.api.HashFactory.BA;

import java.math.BigInteger;
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

  @Override
  public int compareTo(final Hash other) {
    return other.compareTo(data);
  }

  @Override
  public byte[] getDigest() {
    return data;
  }

  @Override
  public Hash getDiscrete() {
    return this;
  }

  @Override
  public long applyToLong(final ByteToLongOperator rawOperator) {
    return rawOperator.apply(data, 0, 1);
  }

  @Override
  public long applyReverseToLong(final ByteToLongOperator rawOperator) {
    return rawOperator.apply(data, 31, -1);
  }

  @Override
  public int applyToInt(final ByteToIntOperator rawOperator) {
    return rawOperator.apply(data, 0, 1);
  }

  @Override
  public int applyReverseToInt(final ByteToIntOperator rawOperator) {
    return rawOperator.apply(data, 31, -1);
  }

  @Override
  public BigInteger toBigInteger() {
    return new BigInteger(1, data);
  }

  @Override
  public void copyHashTo(final byte[] to, final int offset) {
    System.arraycopy(this.data, 0, to, offset, 32);
  }

  @Override
  public void copyHashToVolatile(final byte[] to, int offset) {
    for (final byte b : this.data) {
      BA.setVolatile(to, offset++, b);
    }
  }

  public void update(final MessageDigest messageDigest) {
    for (int i = 31; i >= 0; --i) {
      messageDigest.update(data[i]);
    }
  }

  @Override
  public void copyToReverse(final byte[] to, int offset) {
    for (final byte bite : this.data) {
      to[offset--] = bite;
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
  public int compareTo(final byte[] other, int offset) {
    return Arrays.compare(other, offset, offset + 32, data, 0, 32);
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
