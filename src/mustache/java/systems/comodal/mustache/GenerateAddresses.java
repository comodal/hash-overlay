package systems.comodal.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

final class GenerateAddresses {

  static final String addressesSrcDirectory = GenerateHashClasses.apiSrcDirectory
      + "addresses/";
  private static final String genSrcDirectory = addressesSrcDirectory + "gen/";

  private GenerateAddresses() {
  }

  public static void main(final String[] args) {
    final boolean dryRun = args.length > 0 && args[0].equalsIgnoreCase("dryRun");
    if (!dryRun) {
      clean();
    }

    if (!dryRun) {
      final MustacheFactory mf = new DefaultMustacheFactory();
      generate(mf);
    }
  }

  static void generate(final MustacheFactory mf) {
    for (final AddrFactory addrFactory : AddrFactory.values()) {
      Generate.generate(mf, "addresses/addr_interface.mustache", addrFactory,
          addressesSrcDirectory + addrFactory.name() + ".java");
      for (final Version version : addrFactory.versions) {
        Generate.generate(mf, "addresses/addr_value.mustache", version,
            genSrcDirectory + "V" + version.versionString + version.interfaceName + "Value.java");
        Generate.generate(mf, "addresses/addr_offset.mustache", version,
            genSrcDirectory + "OffsetV" + version.versionString + version.interfaceName + ".java");
        Generate.generate(mf, "addresses/addr_reverse.mustache", version,
            genSrcDirectory + "ReverseV" + version.versionString + version.interfaceName + ".java");
      }
    }
  }

  static void clean() {
    try {
      Files.walk(Paths.get(addressesSrcDirectory), 1)
          .map(Path::toFile)
          .filter(File::isFile)
          .forEach(File::delete);
      final Path genPath = Paths.get(genSrcDirectory);
      if (genPath.toFile().exists()) {
        Files.walk(genPath, FileVisitOption.FOLLOW_LINKS)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
      }
      genPath.toFile().mkdir();
    } catch (final IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }

  static Version ver(final List<String> bytes) {
    return new Version(bytes.stream().collect(Collectors.joining()),
        bytes.stream().map(bite -> "(byte) 0x" + bite).collect(Collectors.joining(", ")));
  }

  public enum AddrFactory {

    Sha256RipeMd160Check4DoubleSha256("RIPEMD160", 20, 4,
        "SHA256", "messageDigest.digest(messageDigest.digest())",
        "RIPEMD160.FACTORY.hashRaw(SHA256.FACTORY.hashRaw(data, offset, len))",
        new Version[]{
            // Bitcoin https://github.com/bitcoin/bitcoin/blob/master/src/chainparams.cpp#L130
            ver(List.of("00")),
            ver(List.of("05")),
            ver(List.of("6F")),
            ver(List.of("C4")),
            // Litecoin https://github.com/litecoin-project/litecoin/blob/master/src/chainparams.cpp#L127
            ver(List.of("30")),
            ver(List.of("32")),
            ver(List.of("3A")),
            // Zcash https://github.com/zcash/zips/blob/master/protocol/protocol.pdf
            // 5.6.1 Encodings of Addresses and Keys - Transparent Payment Addresses
            // https://github.com/zcash/zcash/blob/master/src/chainparams.cpp
            ver(List.of("1C", "BD")),
            ver(List.of("1C", "BA")),
            ver(List.of("1C", "B8")),
            ver(List.of("1D", "25"))
        }),
    //    Keccak256Last160("KECCAK256", 20, 0,
    //        "KECCAK256", "new byte[0]",
    //        "Arrays.copyOfRange(KECCAK256.FACTORY.hashRaw(data, offset, len), 12, 32)",
    //        new Version[]{
    //            // http://ethereum.stackexchange.com/questions/3542/how-are-ethereum-addresses-generated
    //            ver(List.of("00", "78")) // '0x' in ASCII
    //        }),
    Spend256View256Check4Keccac256(null, 64, 4,
        "KECCAK256", "messageDigest.digest()",
        "java.util.Arrays.copyOfRange(data, offset, len)",
        new Version[]{
            // https://github.com/monero-project/monero/blob/master/src/cryptonote_config.h#L141
            ver(List.of("12")),
            ver(List.of("13")),
            ver(List.of("35")),
            ver(List.of("36"))
        }),
    Pay256Send256Check4DoubleSha256(null, 64, 4,
        "SHA256", "messageDigest.digest(messageDigest.digest())",
        "java.util.Arrays.copyOfRange(data, offset, len)",
        new Version[]{
            // Zcash https://github.com/zcash/zips/blob/master/protocol/protocol.pdf
            // 5.6.3 Encodings of Addresses and Keys - Shielded Payment Addresses
            ver(List.of("16", "9A")),
            ver(List.of("16", "B6"))
        }),
    Pad4Spend252Check4DoubleSha256(null, 32, 4,
        "SHA256", "messageDigest.digest()",
        "java.util.Arrays.copyOfRange(data, offset, len)",
        new Version[]{
            // Zcash https://github.com/zcash/zips/blob/master/protocol/protocol.pdf
            // 5.6.4 Encodings of Addresses and Keys - Spending Keys
            ver(List.of("AB", "36")),
            ver(List.of("AC", "08"))
        });

    public final String interfaceName;
    public final String digestHash;
    public final int digestLength;
    public final int offsetLength;
    public final int checksumLength;
    public final String checksumHash;
    public final String applyChecksumDigest;
    public final String create;
    public final Version[] versions;

    AddrFactory(final String digestHash,
        final int digestLength,
        final int checksumLength,
        final String checksumHash,
        final String applyChecksumDigest,
        final String create,
        final Version[] versions) {
      this.interfaceName = name();
      this.digestHash = digestHash;
      this.digestLength = digestLength;
      this.offsetLength = digestLength - 1;
      this.checksumLength = checksumLength;
      this.checksumHash = checksumHash;
      this.applyChecksumDigest = applyChecksumDigest;
      this.create = create;
      this.versions = versions;
      for (final Version version : versions) {
        version.interfaceName = this.interfaceName;
      }
    }
  }

  public static final class Version {

    public final String versionString;
    public final String versionBytes;
    public String interfaceName;

    public Version(final String versionString, final String versionBytes) {
      this.versionString = versionString;
      this.versionBytes = versionBytes;
    }
  }
}
