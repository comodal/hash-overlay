package systems.comodal.hash.addresses.api;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

public interface Addr {

  AddrFactory<? extends Addr> getAddrFactory();

  default int getDigestLength() {
    return getAddrFactory().getDigestLength();
  }

  default byte[] getVersion() {
    return getAddrFactory().getVersion();
  }

  default int getVerDigestLength() {
    return getAddrFactory().getVerDigestLength();
  }

  default HashFactory<? extends Hash> getChecksumHashFactory() {
    return getAddrFactory().getChecksumHashFactory();
  }

  default int getChecksumLength() {
    return getAddrFactory().getChecksumLength();
  }

  default int getChecksummedVerDigestLength() {
    return getAddrFactory().getChecksummedVerDigestLength();
  }

  default byte[] getChecksum() {
    final byte[] checksum = new byte[getChecksumLength()];
    getChecksum(checksum, 0);
    return checksum;
  }

  void getChecksum(final byte[] out, final int offset);

  default byte[] getVerDigest() {
    final byte[] verDigest = new byte[getVerDigestLength()];
    getVerDigest(verDigest, 0);
    return verDigest;
  }

  default void getVerDigest(final byte[] out, final int offset) {
    System.arraycopy(getVersion(), 0, out, offset, getVersion().length);
    copyTo(out, offset + getVersion().length);
  }

  default byte[] getChecksummedVerDigest() {
    final byte[] checksummedVerDigest = new byte[getChecksummedVerDigestLength()];
    getChecksummedVerDigest(checksummedVerDigest, 0);
    return checksummedVerDigest;
  }

  default void getChecksummedVerDigest(final byte[] out, final int offset) {
    System.arraycopy(getVersion(), 0, out, offset, getVersion().length);
    copyTo(out, offset + 1);
    getChecksum(out, offset + getVerDigestLength());
  }

  byte[] getBackingData();

  int getOffset();

  ByteOrder getByteOrder();

  byte[] copy();

  byte[] copyReverse();

  void copyTo(final byte[] to, final int offset);

  void copyToReverse(final byte[] to, final int offset);

  /**
   * If the current Hash is already discrete this method should return itself.
   *
   * @return A Hash instance backed by a dedicated byte array with a digestLength exactly the same
   * as the number of bytes that constitute the digest.
   */
  Addr getDiscrete();

  /**
   * If the current Hash is already discrete this method should return the backing array.
   *
   * @return A byte array with a digestLength exactly the same as the number of bytes that
   * constitute the digest.
   */
  byte[] getDiscreteRaw();

  void update(final MessageDigest messageDigest);

  void updateReverse(final MessageDigest messageDigest);

  boolean digestEquals(final byte[] data);

  boolean digestEquals(final byte[] data, int offset);

  boolean digestEqualsReverse(final byte[] data, int offset);

  int compareTo(final Addr other);

  int compareDigestTo(final byte[] other);

  int compareDigestTo(final byte[] other, int offset);

  int compareDigestToReverse(final byte[] other, int offset);
}
