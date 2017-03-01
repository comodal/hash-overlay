package systems.comodal.mustache;

import com.github.mustachejava.MustacheFactory;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import systems.comodal.mustache.GenerateHashClasses.Digest;

final class GenerateBenchmarks {

  static final String benchSrcDirectory = "src/jmh/java/systems/comodal/hash/benchmark/";

  private GenerateBenchmarks() {
  }

  static void generate(final MustacheFactory mf, final List<Digest> digests) {
    JmhBenchmarkScope benchScope = new JmhBenchmarkScope(digests);
    Generate.generate(mf, "digest_bench.mustache", benchScope,
        benchSrcDirectory + benchScope.className + ".java");
  }

  static void clean() {
    try {
      Files.walk(Paths.get(GenerateBenchmarks.benchSrcDirectory), 1)
          .map(Path::toFile)
          .filter(File::isFile)
          .filter(file -> file.getName().endsWith("ByteBench.java"))
          .forEach(File::delete);
    } catch (final IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }

  public static final class JmhBenchmarkScope {

    public final String className;
    public final List<Digest> digestAlgos;

    public JmhBenchmarkScope(final List<Digest> digestAlgos) {
      this.className = "Digest" + digestAlgos.get(0).digestLength + "ByteBench";
      this.digestAlgos = digestAlgos;
    }
  }
}
