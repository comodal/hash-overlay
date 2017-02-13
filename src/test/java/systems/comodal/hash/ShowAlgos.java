package systems.comodal.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

class ShowAlgos {

  private static final void showHashAlgorithms(final Provider prov, final Class<?> typeClass) {
    final String type = typeClass.getSimpleName();
    System.out.println("Provider: " + prov);
    prov.getServices().stream()
        .filter(service -> service.getType().equalsIgnoreCase(type))
        .forEach(service -> System.out.println(service.getAlgorithm()));
  }

  public static void main(String[] args) throws NoSuchAlgorithmException {
    Security.addProvider(new BouncyCastleProvider());
    for (final Provider provider : Security.getProviders()) {
      showHashAlgorithms(provider, MessageDigest.class);
    }
  }
}
