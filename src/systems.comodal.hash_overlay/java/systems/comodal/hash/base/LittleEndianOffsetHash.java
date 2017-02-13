package systems.comodal.hash.base;

import static systems.comodal.hash.HashFactory.BA;

import java.math.BigInteger;
import java.security.MessageDigest;
import systems.comodal.hash.Hash;

abstract class LittleEndianOffsetHash implements Hash {

  final byte[] data;
  final int offset;

  LittleEndianOffsetHash(final byte[] data, final int offset) {
    this.data = data;
    this.offset = offset + getDigestLength() - 1;
  }

  @Override
  public int hashCode() {
    return (data[0] & 0xFF)
        | (data[1] & 0xFF) << 8
        | (data[2] & 0xFF) << 16
        | (data[3] & 0xFF) << 24;
  }

  @Override
  public int compareTo(final Hash other) {
    return other.compareToReverse(data, offset);
  }

  @Override
  public BigInteger toBigInteger() {
    return new BigInteger(1, getDigest());
  }

  @Override
  public long applyToLong(final ByteToLongOperator rawOperator) {
    return rawOperator.apply(data, offset, -1);
  }

  @Override
  public long applyReverseToLong(final ByteToLongOperator rawOperator) {
    return rawOperator.apply(data, offset - getDigestLength() - 1, 1);
  }

  @Override
  public int applyToInt(final ByteToIntOperator rawOperator) {
    return rawOperator.apply(data, offset, -1);
  }

  @Override
  public int applyReverseToInt(final ByteToIntOperator rawOperator) {
    return rawOperator.apply(data, offset - getDigestLength() - 1, 1);
  }

  @Override
  public byte[] getDigest() {
    final byte[] copy = new byte[getDigestLength()];
    for (int i = this.offset, o = 0; o < getDigestLength(); ++o, --i) {
      copy[o] = this.data[i];
    }
    return copy;
  }

  @Override
  public void copyHashTo(final byte[] to, int offset) {
    for (int i = this.offset, max = offset + getDigestLength(); offset < max; --i, ++offset) {
      to[offset] = this.data[i];
    }
  }

  @Override
  public void copyHashToVolatile(final byte[] to, int offset) {
    for (int i = this.offset, max = offset + getDigestLength(); offset < max; ) {
      BA.setVolatile(to, offset++, data[i--]);
    }
  }

  @Override
  public void update(final MessageDigest messageDigest) {
    for (int i = offset - getDigestLength() - 1; i <= offset; ++i) {
      messageDigest.update(data[i]);
    }
  }

  @Override
  public void copyToReverse(final byte[] to, int offset) {
    System.arraycopy(this.data, this.offset - getDigestLength() - 1, to,
        offset - getDigestLength() - 1, getDigestLength());
  }

  @Override
  public boolean equals(final byte[] other, int offset) {
    for (int i = this.offset, max = offset + getDigestLength(); offset < max; --i, ++offset) {
      if (data[i] != other[offset]) {
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
      --index;
    }
    return true;
  }

  @Override
  public boolean equalsReverse(final byte[] other, int offset) {
    for (int i = this.offset, min = i - getDigestLength(); i > min; --i, --offset) {
      if (this.data[i] != other[offset]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int compareTo(final byte[] other, int offset) {
    for (int i = this.offset, min = i - getDigestLength(); i > min; --i, ++offset) {
      if (this.data[i] != other[offset]) {
        return Byte.compare(other[offset], this.data[i]);
      }
    }
    return 0;
  }

  @Override
  public int compareTo(final byte[] other) {
    int offset = this.offset;
    for (final byte b : other) {
      if (this.data[offset] != b) {
        return Byte.compare(b, this.data[offset]);
      }
      --offset;
    }
    return 0;
  }

  @Override
  public int compareToReverse(final byte[] other, int offset) {
    for (int i = this.offset, min = i - getDigestLength(); i > min; --i, --offset) {
      if (this.data[i] != other[offset]) {
        return Byte.compare(other[offset], this.data[i]);
      }
    }
    return 0;
  }
}
