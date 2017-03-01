package systems.comodal.mustache;

import com.github.mustachejava.MustacheFactory;
import java.util.Collections;
import java.util.List;
import systems.comodal.mustache.GenerateHashClasses.Digest;

final class GenerateHashFactoryEnum {

  private static final String testSrcDirectory = "src/test/java/systems/comodal/hash/";

  private GenerateHashFactoryEnum() {
  }

  static void generate(final MustacheFactory mf, final List<Digest> digests) {
    GenerateHashClasses
        .generate(mf, "digest_factory_enum.mustache", new DigestAlgosEnumScope(digests),
            testSrcDirectory + "DigestAlgo.java");
  }

  public static final class DigestAlgosEnumScope {

    public final List<Digest> digestAlgos;
    public final List<Digest> last;

    public DigestAlgosEnumScope(final List<Digest> digestAlgos) {
      this.digestAlgos = digestAlgos.subList(0, digestAlgos.size() - 1);
      this.last = Collections.singletonList(digestAlgos.get(digestAlgos.size() - 1));
    }
  }
}
