package systems.comodal.hash.addresses;

import java.security.MessageDigest;
import systems.comodal.hash.SHA256;
import systems.comodal.hash.addresses.api.Addr;
import systems.comodal.hash.addresses.api.AddrFactory;
import systems.comodal.hash.addresses.gen.OffsetV169APay256Send256Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV169APay256Send256Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V169APay256Send256Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetV16B6Pay256Send256Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV16B6Pay256Send256Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V16B6Pay256Send256Check4DoubleSha256Value;
import systems.comodal.hash.api.HashFactory;

public interface Pay256Send256Check4DoubleSha256 extends Addr {

  static Version getAddrFactory(final int version) {
    switch (version) {
      case 0x169A:
        return Version.x169A;
      case 0x16B6:
        return Version.x16B6;
      default:
        throw new IllegalArgumentException("Unknown address version: " + version);
    }
  }

  default void getChecksum(final byte[] out, final int offset) {
    final MessageDigest messageDigest = getChecksumHashFactory().getMessageDigest();
    messageDigest.update(getVersion());
    update(messageDigest);
    final byte[] verDigestChecksum = messageDigest.digest(messageDigest.digest());
    System.arraycopy(verDigestChecksum, 0, out, offset, getChecksumLength());
  }

  enum Version implements AddrFactory<Pay256Send256Check4DoubleSha256> {

    x169A(new byte[]{(byte) 0x16, (byte) 0x9A}) {
      @Override
      public Pay256Send256Check4DoubleSha256 overlay(final byte[] digest) {
        return new V169APay256Send256Check4DoubleSha256Value(digest);
      }

      @Override
      public Pay256Send256Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV169APay256Send256Check4DoubleSha256(digest, offset);
      }

      @Override
      public Pay256Send256Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV169APay256Send256Check4DoubleSha256(digest, offset);
      }
    },
    x16B6(new byte[]{(byte) 0x16, (byte) 0xB6}) {
      @Override
      public Pay256Send256Check4DoubleSha256 overlay(final byte[] digest) {
        return new V16B6Pay256Send256Check4DoubleSha256Value(digest);
      }

      @Override
      public Pay256Send256Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV16B6Pay256Send256Check4DoubleSha256(digest, offset);
      }

      @Override
      public Pay256Send256Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV16B6Pay256Send256Check4DoubleSha256(digest, offset);
      }
    },
    ;

    private final byte[] version;
    private final int verDigestLength;
    private final int checksummedVerDigestLength;

    Version(final byte[] version) {
      this.version = version;
      this.verDigestLength = version.length + getDigestLength();
      this.checksummedVerDigestLength = verDigestLength + getChecksumLength();
    }

    @Override
    public int getDigestLength() {
      return 64;
    }

    @Override
    public int getOffsetLength() {
      return 63;
    }

    @Override
    public int getVerDigestLength() {
      return verDigestLength;
    }

    @Override
    public byte[] getVersion() {
      return version;
    }

    @Override
    public int getChecksumLength() {
      return 4;
    }

    @Override
    public int getChecksummedVerDigestLength() {
      return checksummedVerDigestLength;
    }

    @Override
    public HashFactory<SHA256> getChecksumHashFactory() {
      return SHA256.FACTORY;
    }

    @Override
    public Pay256Send256Check4DoubleSha256 create(final byte[] data, final int offset,
        final int len) {
      return overlay(java.util.Arrays.copyOfRange(data, offset, len));
    }
  }
}