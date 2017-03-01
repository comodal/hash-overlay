package systems.comodal.mustache;

import static java.net.http.HttpResponse.asInputStream;
import static systems.comodal.mustache.GenerateHashClasses.apiSrcDirectory;

import com.github.mustachejava.MustacheFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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
    try (final InputStream responseBody = HttpRequest.create(new URI(
        "https://raw.githubusercontent.com/multiformats/multihash/master/hashtable.csv"))
        .GET().response().body(asInputStream());
        final BufferedReader buffer = new BufferedReader(new InputStreamReader(responseBody))) {
      final Pattern hexPattern = Pattern.compile("0x([a-fA-F0-9]+)");
      return buffer.lines()
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
