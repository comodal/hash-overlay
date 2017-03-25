package systems.comodal.mustache;

import static systems.comodal.mustache.GenerateHashClasses.apiSrcDirectory;

import com.github.mustachejava.MustacheFactory;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse.BodyHandler;
import systems.comodal.mustache.GenerateHashClasses.Digest;

final class GenerateMultiHashFactory {

  static final String UNKNOWN_MULTIHASH_FN_CODE = "Long.MIN_VALUE";
  private static final String multihashSrcDirectory = apiSrcDirectory + "multihash/";

  private GenerateMultiHashFactory() {
  }

  static void generate(final MustacheFactory mf, final List<Digest> digests) {
    final List<Digest> digestsWithFnCodes = digests.stream()
        .filter(digest -> digest.fnCode != UNKNOWN_MULTIHASH_FN_CODE)
        .sorted(Comparator.comparing(digest -> Integer.parseInt(digest.fnCode.substring(2), 16)))
        .collect(Collectors.toList());
    Generate.generate(mf, "multihash_factory.mustache", new MultihashScope(digestsWithFnCodes),
        multihashSrcDirectory + "HashFactoryFnCodeFactory.java");
  }

  static Map<String, Multihash> getMulthashMap() {
    try {
      final String hashtableCSV = HttpClient.newHttpClient().send(HttpRequest.newBuilder(new URI(
          "https://raw.githubusercontent.com/multiformats/multihash/master/hashtable.csv"))
          .GET().build(), BodyHandler.asString(StandardCharsets.UTF_8)).body();
      final Pattern hexPattern = Pattern.compile("0x([a-fA-F0-9]+)");
      return Arrays.stream(hashtableCSV.split("/n"))
          .map(line -> line.split(","))
          .filter(parts -> parts.length == 3)
          .map(parts -> {
            final Matcher matcher = hexPattern.matcher(parts[2]);
            if (!matcher.find()) {
              return null;
            }
            return new Multihash(GenerateHashClasses
                .formatAlgoName(hardcodedNameFixes(parts[0])),
                "0x" + matcher.group(1).toUpperCase(Locale.ENGLISH));
          }).filter(Objects::nonNull)
          .collect(Collectors.toMap(multiHash -> multiHash.hash, multiHash -> multiHash));
    } catch (IOException ex) {
      throw new UncheckedIOException(ex);
    } catch (InterruptedException | URISyntaxException ex) {
      throw new RuntimeException(ex);
    }
  }

  private static String hardcodedNameFixes(final String name) {
    return name.toUpperCase(Locale.ENGLISH).replaceAll("SHA2", "SHA");
  }

  static final class Multihash {

    final String hash;
    final String fnCode;

    public Multihash(final String hash, final String fnCode) {
      this.hash = hash;
      this.fnCode = fnCode;
    }
  }

  public static final class MultihashScope {

    public final List<Digest> digestAlgos;

    public MultihashScope(final List<Digest> digestAlgos) {
      this.digestAlgos = digestAlgos;
    }
  }
}
