package systems.comodal.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

public final class GenerateHashClasses {

  private static final String baseSrcDirectory =
      "src/systems.comodal.hash_overlay/java/systems/comodal/hash/";

  public static void main(String[] args) throws IOException {
    final MustacheFactory mf = new DefaultMustacheFactory();
    for (final Digest digest : Digest.values()) {
      generate(mf, "hash_interface.mustache", digest, baseSrcDirectory + digest.hash + ".java");
      generate(mf, "discrete.mustache", digest,
          baseSrcDirectory + "base/Discrete" + digest.hash + ".java");
      generate(mf, "bigendian.mustache", digest,
          baseSrcDirectory + "base/BigEndianOffset" + digest.hash + ".java");
      generate(mf, "littleendian.mustache", digest,
          baseSrcDirectory + "base/LittleEndianOffset" + digest.hash + ".java");
    }
  }

  private static void generate(final MustacheFactory mf, final String templateName,
      final Digest digest, final String outputFile) {
    final Mustache mustache = mf.compile(templateName);
    try {
      mustache.execute(new FileWriter(outputFile), digest).flush();
    } catch (IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }

  enum Digest {

    MD5("Md5", "MD5", "16"),
    SHA1("Sha1", "SHA-1", "20");

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
