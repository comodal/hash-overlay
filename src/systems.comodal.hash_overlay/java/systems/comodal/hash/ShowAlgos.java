package systems.comodal.hash;

import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;

class ShowAlgos {

  private static final void showHashAlgorithms(final Provider prov, final Class<?> typeClass) {
    final String type = typeClass.getSimpleName();
    System.out.println("Provider: " + prov);
    prov.getServices().stream()
        .filter(service -> service.getType().equalsIgnoreCase(type))
        .forEach(service -> System.out.println(service.getAlgorithm()));
  }

  public static void main(String[] args) {
    for (final Provider provider : Security.getProviders()) {
      showHashAlgorithms(provider, MessageDigest.class);
    }
  }
}
