package systems.comodal.hash.base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

public abstract class BaseFactory<H extends Hash> implements HashFactory<H> {

  private final ThreadLocal<MessageDigest> messageDigest;

  protected BaseFactory(final String algorithm) {
    this.messageDigest = ThreadLocal.withInitial(() -> {
      try {
        return MessageDigest.getInstance(algorithm);
      } catch (final NoSuchAlgorithmException ex) {
        throw new IllegalArgumentException(ex.getMessage());
      }
    });
  }

  @Override
  public MessageDigest getMessageDigest() {
    return messageDigest.get();
  }

  @Override
  public int getDigestLength() {
    return messageDigest.get().getDigestLength();
  }
}
