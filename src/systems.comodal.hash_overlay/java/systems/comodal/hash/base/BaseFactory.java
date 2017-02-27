package systems.comodal.hash.base;

import java.security.MessageDigest;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.multihash.MultiHash;

public abstract class BaseFactory<H extends Hash> implements HashFactory<H> {

  private final ThreadLocal<MessageDigest> messageDigest;
  private final byte[] multiHashPrefix;

  protected BaseFactory(final String algorithm) {
    this.messageDigest = ThreadLocal
        .withInitial(() -> HashFactory.getMessageDigestUnchecked(algorithm));
    this.multiHashPrefix = getMultiHashFnCode() > 0
        ? MultiHash.createPrefix(getMultiHashFnCode(), getDigestLength())
        : new byte[0];
  }

  @Override
  public MessageDigest getMessageDigest() {
    return messageDigest.get();
  }

  @Override
  public int getDigestLength() {
    return messageDigest.get().getDigestLength();
  }

  @Override
  public byte[] getMultiHashPrefix() {
    return multiHashPrefix;
  }
}
