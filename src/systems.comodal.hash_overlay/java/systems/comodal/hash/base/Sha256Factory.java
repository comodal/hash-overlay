package systems.comodal.hash.base;

import systems.comodal.hash.BaseFactory;
import systems.comodal.hash.Sha256;

public final class Sha256Factory implements BaseFactory<Sha256> {

  @Override
  public Sha256 overlay(final byte[] digest) {
    return new DiscreteSha256(digest);
  }

  @Override
  public Sha256 overlay(final byte[] digest, final int offset) {
    return new BigEndianOffsetSha256(digest, offset);
  }

  @Override
  public Sha256 reverseOverlay(final byte[] digest, final int offset) {
    return new LittleEndianOffsetSha256(digest, offset);
  }
}
