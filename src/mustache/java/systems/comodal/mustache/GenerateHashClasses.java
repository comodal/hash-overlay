package systems.comodal.mustache;

import static systems.comodal.mustache.GenerateMultiHashFactory.UNKNOWN_MULTIHASH_FN_CODE;

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
import systems.comodal.mustache.GenerateMultiHashFactory.Multihash;

public final class GenerateHashClasses {

  static final String apiSrcDirectory =
      "src/systems.comodal.hash_overlay/java/systems/comodal/hash/";
  private static final String genSrcDirectory = apiSrcDirectory + "gen/";

  private static final Pattern nonDigitHyphenPattern = Pattern.compile("(\\D)-");

  static void generate(final MustacheFactory mf, final List<Digest> digests) {
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
  }

  static Map<Integer, List<Digest>> getDigestsByLength(final Map<String, Multihash> multihashMap) {
    final String msgDigestTypeName = MessageDigest.class.getSimpleName();
    final Set<String> dedupe = new HashSet<>(256);
    return Arrays.stream(Security.getProviders())
        .map(Provider::getServices)
        .flatMap(Collection::stream)
        .filter(service -> service.getType().equalsIgnoreCase(msgDigestTypeName))
        .map(Service::getAlgorithm)
        .filter(algorithm -> !algorithm.contains(".")
            && dedupe.add(algorithm.toUpperCase(Locale.ENGLISH)))
        .map(algorithm -> {
          final MessageDigest msgDigest = GenerateHashClasses.getMessageDigest(algorithm);
          final int digestLength = msgDigest.getDigestLength();
          if (digestLength <= 0) {
            return null;
          }
          final String formattedName = GenerateHashClasses.formatAlgoName(algorithm);
          final Multihash multihash = multihashMap.get(formattedName.toUpperCase(Locale.ENGLISH));
          System.out.println(msgDigest.getProvider() + ": " + formattedName);
          return new Digest(formattedName, algorithm, digestLength,
              msgDigest.getProvider().getName() + msgDigest.getProvider().getVersionStr()
                  .replace('.', '_'),
              multihash == null ? UNKNOWN_MULTIHASH_FN_CODE : multihash.fnCode);
        })
        .filter(Objects::nonNull)
        .collect(Collectors.groupingBy(digest -> digest.digestLength));
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

  static void generate(final MustacheFactory mf, final String templateName,
      final Object scope, final String outputFile) {
    try (final FileWriter writer = new FileWriter(outputFile)) {
      mf.compile(templateName).execute(writer, scope);
    } catch (IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }

  static void clean() {
    try {
      Files.walk(Paths.get(apiSrcDirectory), 1)
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

  public static class Digest {

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
