package systems.comodal.hash.addresses;

import java.security.MessageDigest;
import systems.comodal.hash.RIPEMD160;
import systems.comodal.hash.SHA256;
import systems.comodal.hash.addresses.api.Addr;
import systems.comodal.hash.addresses.api.AddrFactory;
import systems.comodal.hash.addresses.gen.OffsetV00Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV00Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V00Sha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetV05Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV05Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V05Sha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetV6FSha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV6FSha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V6FSha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetVC4Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseVC4Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.VC4Sha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetV30Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV30Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V30Sha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetV32Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV32Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V32Sha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetV3ASha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV3ASha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V3ASha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetV1CBDSha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV1CBDSha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V1CBDSha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetV1CBASha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV1CBASha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V1CBASha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetV1CB8Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV1CB8Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V1CB8Sha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetV1D25Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV1D25Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V1D25Sha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.api.HashFactory;

public interface Sha256RipeMd160Check4DoubleSha256 extends Addr {

  static Version getAddrFactory(final int version) {
    switch (version) {
      case 0x00:
        return Version.x00;
      case 0x05:
        return Version.x05;
      case 0x6F:
        return Version.x6F;
      case 0xC4:
        return Version.xC4;
      case 0x30:
        return Version.x30;
      case 0x32:
        return Version.x32;
      case 0x3A:
        return Version.x3A;
      case 0x1CBD:
        return Version.x1CBD;
      case 0x1CBA:
        return Version.x1CBA;
      case 0x1CB8:
        return Version.x1CB8;
      case 0x1D25:
        return Version.x1D25;
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

  enum Version implements AddrFactory<Sha256RipeMd160Check4DoubleSha256> {

    x00(new byte[]{(byte) 0x00}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V00Sha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV00Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV00Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }
    },
    x05(new byte[]{(byte) 0x05}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V05Sha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV05Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV05Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }
    },
    x6F(new byte[]{(byte) 0x6F}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V6FSha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV6FSha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV6FSha256RipeMd160Check4DoubleSha256(digest, offset);
      }
    },
    xC4(new byte[]{(byte) 0xC4}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new VC4Sha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetVC4Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseVC4Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }
    },
    x30(new byte[]{(byte) 0x30}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V30Sha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV30Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV30Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }
    },
    x32(new byte[]{(byte) 0x32}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V32Sha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV32Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV32Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }
    },
    x3A(new byte[]{(byte) 0x3A}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V3ASha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV3ASha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV3ASha256RipeMd160Check4DoubleSha256(digest, offset);
      }
    },
    x1CBD(new byte[]{(byte) 0x1C, (byte) 0xBD}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V1CBDSha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV1CBDSha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV1CBDSha256RipeMd160Check4DoubleSha256(digest, offset);
      }
    },
    x1CBA(new byte[]{(byte) 0x1C, (byte) 0xBA}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V1CBASha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV1CBASha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV1CBASha256RipeMd160Check4DoubleSha256(digest, offset);
      }
    },
    x1CB8(new byte[]{(byte) 0x1C, (byte) 0xB8}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V1CB8Sha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV1CB8Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV1CB8Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }
    },
    x1D25(new byte[]{(byte) 0x1D, (byte) 0x25}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V1D25Sha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV1D25Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV1D25Sha256RipeMd160Check4DoubleSha256(digest, offset);
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
      return 20;
    }

    @Override
    public int getOffsetLength() {
      return 19;
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
    public Sha256RipeMd160Check4DoubleSha256 create(final byte[] data, final int offset,
        final int len) {
      return overlay(RIPEMD160.FACTORY.hashRaw(SHA256.FACTORY.hashRaw(data, offset, len)));
    }
  }
}