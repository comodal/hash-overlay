package systems.comodal.hash.base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import systems.comodal.hash.Hash;
import systems.comodal.hash.HashFactory;

public abstract class BaseFactory<H extends Hash> implements HashFactory<H> {

  private final ThreadLocal<MessageDigest> messageDigest;

  public BaseFactory(final String algorithm) {
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
