package systems.comodal.hash;

import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import systems.comodal.hash.base.Sha256Factory;
import systems.comodal.hash.base.Sha3_256Factory;

public enum DigestAlgo implements HashFactory<Hash> { // Hopefully someday Enum's will be generic :/

  SHA_256("SHA-256", Sha256Factory.class),
  SHA3_256("SHA3-256", Sha3_256Factory.class);

  public final int digestLength;
  public final Hash zero;

  private final ThreadLocal<MessageDigest> messageDigest;
  private final BaseFactory<? extends Hash> baseFactory;

  DigestAlgo(final String algorithm,
      final Class<? extends BaseFactory<? extends Hash>> bashFactoryClass) {
    this.messageDigest = ThreadLocal
        .withInitial(() -> {
          try {
            return MessageDigest.getInstance(algorithm);
          } catch (final NoSuchAlgorithmException ex) {
            throw new IllegalArgumentException(ex.getMessage());
          }
        });
    this.digestLength = messageDigest.get().getDigestLength();
    try {
      this.baseFactory = bashFactoryClass.getConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException
        | NoSuchMethodException ex) {
      throw new RuntimeException(ex);
    }
    this.zero = baseFactory.overlay(new byte[digestLength]);
  }

  @Override
  public MessageDigest getMessageDigest() {
    return messageDigest.get();
  }

  @Override
  public int getDigestLength() {
    return digestLength;
  }

  @Override
  public Hash overlay(final byte[] digest) {
    return baseFactory.overlay(digest);
  }

  @Override
  public Hash overlay(final byte[] digest, final int offset) {
    return baseFactory.overlay(digest, offset);
  }

  @Override
  public Hash reverseOverlay(final byte[] digest, final int offset) {
    return baseFactory.reverseOverlay(digest, offset);
  }
}
