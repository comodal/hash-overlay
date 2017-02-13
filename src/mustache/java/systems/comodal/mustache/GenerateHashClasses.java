package systems.comodal.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

public final class GenerateHashClasses {

  private static final String apiSrcDirectory =
      "src/systems.comodal.hash_overlay/java/systems/comodal/hash/";
  private static final String baseSrcDirectory = apiSrcDirectory + "base/";

  public static void main(String[] args) throws IOException {
    final MustacheFactory mf = new DefaultMustacheFactory();
    for (final Digest digest : Digest.values()) {
      generate(mf, "hash_interface.mustache", digest, apiSrcDirectory + digest.hash + ".java");
      generate(mf, "discrete.mustache", digest,
          baseSrcDirectory + "Discrete" + digest.hash + ".java");
      generate(mf, "bigendian.mustache", digest,
          baseSrcDirectory + "BigEndianOffset" + digest.hash + ".java");
      generate(mf, "littleendian.mustache", digest,
          baseSrcDirectory + "LittleEndianOffset" + digest.hash + ".java");
    }
  }

  private static void generate(final MustacheFactory mf, final String templateName,
      final Digest digest, final String outputFile) {
    try (final FileWriter writer = new FileWriter(outputFile)) {
      mf.compile(templateName).execute(writer, digest);
    } catch (IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }

  enum Digest {

    MD5("Md5", "MD5", "16"),
    RIPEMD160("RipeMd160", "RIPEMD160", "20"),
    SHA1("Sha1", "SHA-1", "20"),
    SHA256("Sha256", "SHA-256", "32"),
    SHA3_256("Sha3_256", "SHA3-256", "32");

    public final String hash;
    public final String algoName;
    public final String digestLength;

    Digest(final String hash, final String algoName, final String digestLength) {
      this.hash = hash;
      this.algoName = algoName;
      this.digestLength = digestLength;
    }
  }
}
