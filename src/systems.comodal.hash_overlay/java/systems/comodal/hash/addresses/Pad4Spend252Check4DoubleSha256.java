package systems.comodal.hash.addresses;

import java.security.MessageDigest;
import systems.comodal.hash.SHA256;
import systems.comodal.hash.addresses.api.Addr;
import systems.comodal.hash.addresses.api.AddrFactory;
import systems.comodal.hash.addresses.gen.OffsetVAB36Pad4Spend252Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseVAB36Pad4Spend252Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.VAB36Pad4Spend252Check4DoubleSha256Value;
import systems.comodal.hash.addresses.gen.OffsetVAC08Pad4Spend252Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.ReverseVAC08Pad4Spend252Check4DoubleSha256;
import systems.comodal.hash.addresses.gen.VAC08Pad4Spend252Check4DoubleSha256Value;
import systems.comodal.hash.api.HashFactory;

public interface Pad4Spend252Check4DoubleSha256 extends Addr {

  static Version getAddrFactory(final int version) {
    switch (version) {
      case 0xAB36:
        return Version.xAB36;
      case 0xAC08:
        return Version.xAC08;
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

  enum Version implements AddrFactory<Pad4Spend252Check4DoubleSha256> {

    xAB36(new byte[]{(byte) 0xAB, (byte) 0x36}) {
      @Override
      public Pad4Spend252Check4DoubleSha256 overlay(final byte[] digest) {
        return new VAB36Pad4Spend252Check4DoubleSha256Value(digest);
      }

      @Override
      public Pad4Spend252Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetVAB36Pad4Spend252Check4DoubleSha256(digest, offset);
      }

      @Override
      public Pad4Spend252Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseVAB36Pad4Spend252Check4DoubleSha256(digest, offset);
      }
    },
    xAC08(new byte[]{(byte) 0xAC, (byte) 0x08}) {
      @Override
      public Pad4Spend252Check4DoubleSha256 overlay(final byte[] digest) {
        return new VAC08Pad4Spend252Check4DoubleSha256Value(digest);
      }

      @Override
      public Pad4Spend252Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new OffsetVAC08Pad4Spend252Check4DoubleSha256(digest, offset);
      }

      @Override
      public Pad4Spend252Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new ReverseVAC08Pad4Spend252Check4DoubleSha256(digest, offset);
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
      return 32;
    }

    @Override
    public int getOffsetLength() {
      return 31;
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
    public Pad4Spend252Check4DoubleSha256 create(final byte[] data, final int offset,
        final int len) {
      return overlay(java.util.Arrays.copyOfRange(data, offset, len));
    }
  }
}