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
import systems.comodal.hash.addresses.gen.OffsetV48Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseV48Sha256RipeMd160Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.V48Sha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.api.HashFactory;

public interface Sha256RipeMd160Check4DoubleSha256 extends Addr {

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
    x48(new byte[]{(byte) 0x48}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V48Sha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetV48Sha256RipeMd160Check4DoubleSha256(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseV48Sha256RipeMd160Check4DoubleSha256(digest, offset);
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
      return RIPEMD160.FACTORY.getDigestLength();
    }

    @Override
    public int getOffsetLength() {
      return RIPEMD160.FACTORY.getOffsetLength();
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