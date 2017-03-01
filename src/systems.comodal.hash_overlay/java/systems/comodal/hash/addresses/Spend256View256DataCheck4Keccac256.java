package systems.comodal.hash.addresses;

import java.security.MessageDigest;
import systems.comodal.hash.KECCAK256;
import systems.comodal.hash.addresses.api.Addr;
import systems.comodal.hash.addresses.api.AddrFactory;
import systems.comodal.hash.addresses.gen.OffsetV12Spend256View256DataCheck4Keccac256;
import systems.comodal.hash.addresses.gen.ReverseV12Spend256View256DataCheck4Keccac256;
import systems.comodal.hash.addresses.gen.V12Spend256View256DataCheck4Keccac256Value;
import systems.comodal.hash.addresses.gen.OffsetV13Spend256View256DataCheck4Keccac256;
import systems.comodal.hash.addresses.gen.ReverseV13Spend256View256DataCheck4Keccac256;
import systems.comodal.hash.addresses.gen.V13Spend256View256DataCheck4Keccac256Value;
import systems.comodal.hash.addresses.gen.OffsetV35Spend256View256DataCheck4Keccac256;
import systems.comodal.hash.addresses.gen.ReverseV35Spend256View256DataCheck4Keccac256;
import systems.comodal.hash.addresses.gen.V35Spend256View256DataCheck4Keccac256Value;
import systems.comodal.hash.addresses.gen.OffsetV36Spend256View256DataCheck4Keccac256;
import systems.comodal.hash.addresses.gen.ReverseV36Spend256View256DataCheck4Keccac256;
import systems.comodal.hash.addresses.gen.V36Spend256View256DataCheck4Keccac256Value;
import systems.comodal.hash.api.HashFactory;

public interface Spend256View256DataCheck4Keccac256 extends Addr {

  static Version getAddrFactory(final int version) {
    switch (version) {
      case 0x12:
        return Version.x12;
      case 0x13:
        return Version.x13;
      case 0x35:
        return Version.x35;
      case 0x36:
        return Version.x36;
      default:
        throw new IllegalArgumentException("Unknown address version: " + version);
    }
  }

  default void getChecksum(final byte[] out, final int offset) {
    final MessageDigest messageDigest = getChecksumHashFactory().getMessageDigest();
    messageDigest.update(getVersion());
    update(messageDigest);
    final byte[] verDigestChecksum = messageDigest.digest();
    System.arraycopy(verDigestChecksum, 0, out, offset, getChecksumLength());
  }

  enum Version implements AddrFactory<Spend256View256DataCheck4Keccac256> {

    x12(new byte[]{(byte) 0x12}) {
      @Override
      public Spend256View256DataCheck4Keccac256 overlay(final byte[] digest) {
        return new V12Spend256View256DataCheck4Keccac256Value(digest);
      }

      @Override
      public Spend256View256DataCheck4Keccac256 overlay(final byte[] digest, final int offset) {
        return new OffsetV12Spend256View256DataCheck4Keccac256(digest, offset);
      }

      @Override
      public Spend256View256DataCheck4Keccac256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV12Spend256View256DataCheck4Keccac256(digest, offset);
      }
    },
    x13(new byte[]{(byte) 0x13}) {
      @Override
      public Spend256View256DataCheck4Keccac256 overlay(final byte[] digest) {
        return new V13Spend256View256DataCheck4Keccac256Value(digest);
      }

      @Override
      public Spend256View256DataCheck4Keccac256 overlay(final byte[] digest, final int offset) {
        return new OffsetV13Spend256View256DataCheck4Keccac256(digest, offset);
      }

      @Override
      public Spend256View256DataCheck4Keccac256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV13Spend256View256DataCheck4Keccac256(digest, offset);
      }
    },
    x35(new byte[]{(byte) 0x35}) {
      @Override
      public Spend256View256DataCheck4Keccac256 overlay(final byte[] digest) {
        return new V35Spend256View256DataCheck4Keccac256Value(digest);
      }

      @Override
      public Spend256View256DataCheck4Keccac256 overlay(final byte[] digest, final int offset) {
        return new OffsetV35Spend256View256DataCheck4Keccac256(digest, offset);
      }

      @Override
      public Spend256View256DataCheck4Keccac256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV35Spend256View256DataCheck4Keccac256(digest, offset);
      }
    },
    x36(new byte[]{(byte) 0x36}) {
      @Override
      public Spend256View256DataCheck4Keccac256 overlay(final byte[] digest) {
        return new V36Spend256View256DataCheck4Keccac256Value(digest);
      }

      @Override
      public Spend256View256DataCheck4Keccac256 overlay(final byte[] digest, final int offset) {
        return new OffsetV36Spend256View256DataCheck4Keccac256(digest, offset);
      }

      @Override
      public Spend256View256DataCheck4Keccac256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV36Spend256View256DataCheck4Keccac256(digest, offset);
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
    public HashFactory<KECCAK256> getChecksumHashFactory() {
      return KECCAK256.FACTORY;
    }

    @Override
    public Spend256View256DataCheck4Keccac256 create(final byte[] data, final int offset,
        final int len) {
      return overlay(java.util.Arrays.copyOfRange(data, offset, len));
    }
  }
}