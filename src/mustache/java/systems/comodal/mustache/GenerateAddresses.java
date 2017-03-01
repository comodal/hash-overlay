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

final class GenerateAddresses {

  static final String addressesSrcDirectory = GenerateHashClasses.apiSrcDirectory +
      "addresses/";
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

  static Version ver(final String versionString, final String versionBytes) {
    return new Version(versionString, versionBytes);
  }

  public enum AddrFactory {

    Sha256RipeMd160Check4DoubleSha256("RIPEMD160", 4, "SHA256", true,
        "RIPEMD160.FACTORY.hashRaw(SHA256.FACTORY.hashRaw(data, offset, len))",
        new Version[]{
            // Bitcoin
            ver("00", "0x00"),
            ver("05", "0x05"),
            ver("6F", "0x6F"),
            ver("C4", "(byte) 0xC4"),
            // Litecoin
            ver("48", "0x48")
        });

    public final String interfaceName;
    public final String digestHash;
    public final int checksumLength;
    public final String checksumHash;
    public final boolean doubleHashChecksum;
    public final String create;
    public final Version[] versions;

    AddrFactory(final String digestHash,
        final int checksumLength,
        final String checksumHash,
        final boolean doubleHashChecksum,
        final String create,
        final Version[] versions) {
      this.interfaceName = name();
      this.digestHash = digestHash;
      this.checksumLength = checksumLength;
      this.checksumHash = checksumHash;
      this.doubleHashChecksum = doubleHashChecksum;
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
