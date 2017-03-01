package systems.comodal.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import java.security.Security;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import systems.comodal.mustache.GenerateHashClasses.Digest;
import systems.comodal.mustache.GenerateMultiHashFactory.Multihash;

final class Generate {

  private Generate() {
  }

  public static void main(final String[] args) {
    final boolean dryRun = args.length > 0 && args[0].equalsIgnoreCase("dryRun");
    if (!dryRun) {
      clean();
    }

    Security.addProvider(new BouncyCastleProvider());

    final Map<String, Multihash> multihashMap = GenerateMultiHashFactory.getMulthashMap();

    final Map<Integer, List<Digest>> digestsByLength = GenerateHashClasses
        .getDigestsByLength(multihashMap);

    if (!dryRun) {
      final MustacheFactory mf = new DefaultMustacheFactory();
      digestsByLength.forEach((length, digests) -> {
        GenerateBenchmarks.generate(mf, digests);
        GenerateHashClasses.generate(mf, digests);
      });

      final List<Digest> digests = digestsByLength.values().stream()
          .flatMap(Collection::stream)
          .sorted(Comparator.comparing(digest -> digest.hash))
          .collect(Collectors.toList());

      GenerateHashFactoryEnum.generate(mf, digests);
      GenerateMultiHashFactory.generate(mf, digests);
    }
  }

  static void clean() {
    GenerateBenchmarks.clean();
    GenerateHashClasses.clean();
  }
}
