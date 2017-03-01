package systems.comodal.hash.addresses;

import java.security.MessageDigest;
import systems.comodal.hash.RIPEMD160;
import systems.comodal.hash.SHA256;
import systems.comodal.hash.addresses.api.Addr;
import systems.comodal.hash.addresses.api.AddrFactory;
import systems.comodal.hash.addresses.gen.V0Sha256RipeMd160Check4DoubleSha256Offset;
import systems.comodal.hash.addresses.gen.V0Sha256RipeMd160Check4DoubleSha256Reverse;
import systems.comodal.hash.addresses.gen.V0Sha256RipeMd160Check4DoubleSha256Value;
import systems.comodal.hash.api.Hash;
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

    x00(new byte[]{0x00}) {
      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest) {
        return new V0Sha256RipeMd160Check4DoubleSha256Value(digest);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 overlay(final byte[] digest, final int offset) {
        return new V0Sha256RipeMd160Check4DoubleSha256Offset(digest, offset);
      }

      @Override
      public Sha256RipeMd160Check4DoubleSha256 reverseOverlay(final byte[] digest,
          final int offset) {
        return new V0Sha256RipeMd160Check4DoubleSha256Reverse(digest, offset);
      }
    };

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
    public HashFactory<? extends Hash> getChecksumHashFactory() {
      return SHA256.FACTORY;
    }

    @Override
    public Sha256RipeMd160Check4DoubleSha256 create(final byte[] data, final int offset,
        final int len) {
      return overlay(RIPEMD160.FACTORY.hashRaw(SHA256.FACTORY.hashRaw(data, offset, len)));
    }
  }
}
