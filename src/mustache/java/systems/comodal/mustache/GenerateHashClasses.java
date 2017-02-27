package systems.comodal.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import systems.comodal.mustache.GenerateMultiHashFactory.Multihash;

public final class GenerateHashClasses {

  private static final String apiSrcDirectory =
      "src/systems.comodal.hash_overlay/java/systems/comodal/hash/";
  private static final String genSrcDirectory = apiSrcDirectory + "gen/";
  private static final String multihashSrcDirectory = apiSrcDirectory + "multihash/";
  private static final String testSrcDirectory = "src/test/java/systems/comodal/hash/";
  private static final String benchSrcDirectory = "src/jmh/java/systems/comodal/hash/benchmark/";

  private static final Pattern nonDigitHyphenPattern = Pattern.compile("(\\D)-");

  private static final String UNKNOWN_MULTIHASH_FN_CODE = "Long.MIN_VALUE";

  public static void main(final String[] args) throws IOException {
    final boolean dryRun = args.length > 0 && args[0].equalsIgnoreCase("dryRun");

    if (!dryRun) {
      Files.walk(Paths.get(apiSrcDirectory), 1)
          .map(Path::toFile)
          .filter(File::isFile)
          .forEach(File::delete);

      Files.walk(Paths.get(benchSrcDirectory), 1)
          .map(Path::toFile)
          .filter(File::isFile)
          .filter(file -> file.getName().endsWith("ByteBench.java"))
          .forEach(File::delete);

      final Path genPath = Paths.get(genSrcDirectory);
      if (genPath.toFile().exists()) {
        Files.walk(genPath, FileVisitOption.FOLLOW_LINKS)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
      }
      genPath.toFile().mkdir();
    }

    Security.addProvider(new BouncyCastleProvider());
    final String msgDigestTypeName = MessageDigest.class.getSimpleName();

    final Map<String, Multihash> multihashMap = GenerateMultiHashFactory.getMulthashMap();

    final Set<String> dedupe = new HashSet<>();

    final Map<Integer, List<Digest>> digestsByLength = Arrays.stream(Security.getProviders())
        .map(Provider::getServices)
        .flatMap(Collection::stream)
        .filter(service -> service.getType().equalsIgnoreCase(msgDigestTypeName))
        .map(Service::getAlgorithm)
        .filter(algorithm -> !algorithm.contains(".")
            && dedupe.add(algorithm.toUpperCase(Locale.ENGLISH)))
        .map(algorithm -> {
          final MessageDigest msgDigest = getMessageDigest(algorithm);
          final int digestLength = msgDigest.getDigestLength();
          if (digestLength <= 0) {
            return null;
          }
          final String formattedName = formatAlgoName(algorithm);
          final Multihash multihash = multihashMap.get(formattedName.toUpperCase(Locale.ENGLISH));
          System.out.println(msgDigest.getProvider() + ": " + formattedName);
          return new Digest(formattedName, algorithm, digestLength,
              msgDigest.getProvider().getName() + msgDigest.getProvider().getVersionStr()
                  .replace('.', '_'),
              multihash == null ? UNKNOWN_MULTIHASH_FN_CODE : multihash.fnCode);
        })
        .filter(Objects::nonNull)
        .collect(Collectors.groupingBy(digest -> digest.digestLength));

    if (!dryRun) {
      final MustacheFactory mf = new DefaultMustacheFactory();
      digestsByLength.forEach((length, digests) -> {
        JmhBenchmarkScope benchScope = new JmhBenchmarkScope(digests);
        generate(mf, "digest_bench.mustache", benchScope,
            benchSrcDirectory + benchScope.className + ".java");
        digests.forEach(digest -> {
          System.out.format("%s : %s : %d%n", digest.hash, digest.algoName, digest.digestLength);
          generate(mf, "hash_interface.mustache", digest, apiSrcDirectory + digest.hash + ".java");
          generate(mf, "value.mustache", digest,
              genSrcDirectory + digest.hash + "Value.java");
          generate(mf, "bigendian.mustache", digest,
              genSrcDirectory + "BigEndianOffset" + digest.hash + ".java");
          generate(mf, "littleendian.mustache", digest,
              genSrcDirectory + "LittleEndianOffset" + digest.hash + ".java");
        });
      });

      final List<Digest> digests = digestsByLength.values().stream()
          .flatMap(Collection::stream)
          .sorted(Comparator.comparing(digest -> digest.hash))
          .collect(Collectors.toList());
      generate(mf, "digest_factory_enum.mustache", new DigestAlgosEnumScope(digests),
          testSrcDirectory + "DigestAlgo.java");

      final List<Digest> digestsWithFnCodes = digests.stream()
          .filter(digest -> digest.fnCode != UNKNOWN_MULTIHASH_FN_CODE)
          .sorted(Comparator.comparing(digest -> Integer.parseInt(digest.fnCode.substring(2), 16)))
          .collect(Collectors.toList());
      generate(mf, "multihash_factory.mustache", new MultihashScope(digestsWithFnCodes),
          multihashSrcDirectory + "HashFactoryFnCodeFactory.java");
    }
  }

  static String formatAlgoName(final String algorithm) {
    final Matcher matcher = nonDigitHyphenPattern.matcher(algorithm);
    return (matcher.find() ? matcher.replaceAll("$1") : algorithm)
        .replace('-', '_')
        .replace('/', '_');
  }

  private static MessageDigest getMessageDigest(final String algorithm) {
    try {
      return MessageDigest.getInstance(algorithm);
    } catch (final NoSuchAlgorithmException ex) {
      throw new IllegalArgumentException(ex.getMessage());
    }
  }

  private static void generate(final MustacheFactory mf, final String templateName,
      final Object scope, final String outputFile) {
    try (final FileWriter writer = new FileWriter(outputFile)) {
      mf.compile(templateName).execute(writer, scope);
    } catch (IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }

  public static final class MultihashScope {

    public final List<Digest> digestAlgos;

    public MultihashScope(final List<Digest> digestAlgos) {
      this.digestAlgos = digestAlgos;
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

  public static final class DigestAlgosEnumScope {

    public final List<Digest> digestAlgos;
    public final List<Digest> last;

    public DigestAlgosEnumScope(final List<Digest> digestAlgos) {
      this.digestAlgos = digestAlgos.subList(0, digestAlgos.size() - 1);
      this.last = Collections.singletonList(digestAlgos.get(digestAlgos.size() - 1));
    }
  }

  public static final class Digest {

    public final String hash;
    public final String algoName;
    public final int digestLength;
    public final int digestOffset;
    public final String provider;
    public final String fnCode;

    Digest(final String hash, final String algoName, final int digestLength,
        final String provider, final String fnCode) {
      this.hash = hash;
      this.algoName = algoName;
      this.digestLength = digestLength;
      this.digestOffset = digestLength - 1;
      this.provider = provider;
      this.fnCode = fnCode;
    }
  }
}
