package systems.comodal.hash.base;

import static systems.comodal.hash.api.HashFactory.BA;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import systems.comodal.hash.api.Hash;

public abstract class BigEndianOffsetHash implements Hash {

  final byte[] data;
  final int offset;

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
  public int compareTo(final Hash other) {
    return other.compareTo(data, offset);
  }

  @Override
  public BigInteger toBigInteger() {
    return new BigInteger(1, data, offset, getDigestLength());
  }

  @Override
  public long applyToLong(final ByteToLongOperator rawOperator) {
    return rawOperator.apply(data, offset, 1);
  }

  @Override
  public long applyReverseToLong(final ByteToLongOperator rawOperator) {
    return rawOperator.apply(data, offset + getDigestLength() - 1, -1);
  }

  @Override
  public int applyToInt(final ByteToIntOperator rawOperator) {
    return rawOperator.apply(data, offset, 1);
  }

  @Override
  public int applyReverseToInt(final ByteToIntOperator rawOperator) {
    return rawOperator.apply(data, offset + getDigestLength() - 1, -1);
  }

  @Override
  public byte[] getDigest() {
    final byte[] copy = new byte[getDigestLength()];
    System.arraycopy(this.data, this.offset, copy, 0, getDigestLength());
    return copy;
  }

  @Override
  public void copyHashTo(final byte[] to, final int offset) {
    System.arraycopy(this.data, this.offset, to, offset, getDigestLength());
  }

  @Override
  public void copyHashToVolatile(final byte[] to, int offset) {
    for (int i = this.offset, max = i + getDigestLength(); i < max; ) {
      BA.setVolatile(to, offset++, data[i++]);
    }
  }

  @Override
  public void update(final MessageDigest messageDigest) {
    for (int i = offset + getDigestLength() - 1; i >= offset; --i) {
      messageDigest.update(data[i]);
    }
  }

  @Override
  public void copyToReverse(final byte[] to, int offset) {
    for (int i = this.offset, max = i + getDigestLength(); i < max; --offset, ++i) {
      to[offset] = this.data[i];
    }
  }

  @Override
  public boolean equals(final byte[] other, int offset) {
    for (int i = this.offset, max = this.offset + getDigestLength(); i < max; ++i, ++offset) {
      if (this.data[i] != other[offset]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean equals(final byte[] other) {
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
  public boolean equalsReverse(final byte[] other, int offset) {
    for (int i = this.offset, max = i + getDigestLength(); i < max; ++i, --offset) {
      if (this.data[i] != other[offset]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int compareTo(final byte[] other, final int offset) {
    return Arrays.compare(other, offset, offset + getDigestLength(), data, this.offset,
        this.offset + getDigestLength());
  }

  @Override
  public int compareTo(final byte[] other) {
    return Arrays
        .compare(other, 0, getDigestLength(), data, this.offset, this.offset + getDigestLength());
  }

  @Override
  public int compareToReverse(final byte[] other, int offset) {
    for (int i = this.offset, max = i + getDigestLength(); i < max; ++i, --offset) {
      if (this.data[i] != other[offset]) {
        return Byte.compare(other[offset], this.data[i]);
      }
    }
    return 0;
  }
}
