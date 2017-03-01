package systems.comodal.mustache;

final class GenerateAddresses {

  private GenerateAddresses() {
  }

  public enum AddrFactory {

  }

  public enum Addr {

    Sha256RipeMd160Check4DoubleSha256("RIPEMD160", 20, "0x00", 4, 21, 25, "SHA256", true);

    public final String hash;
    public final int digestLength;
    public final int digestOffset;
    public final String version;
    public final int verDigestLength;
    public final int checksumLength;
    public final int checksummedVerDigestLength;
    public final String checksumHash;

    Addr(final String hash,
        final int digestLength,
        final String version,
        final int verDigestLength,
        final int checksumLength,
        final int checksummedVerDigestLength,
        final String checksumHash,
        final boolean doubleHashChecksum) {
      this.hash = hash;
      this.digestLength = digestLength;
      this.digestOffset = digestLength - 1;
      this.version = version;
      this.checksumLength = checksumLength;
      this.verDigestLength = verDigestLength;
      this.checksummedVerDigestLength = checksummedVerDigestLength;
      this.checksumHash = checksumHash;
    }
  }
}
