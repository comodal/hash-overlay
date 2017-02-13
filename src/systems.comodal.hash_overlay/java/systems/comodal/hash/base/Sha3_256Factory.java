package systems.comodal.hash.base;

import systems.comodal.hash.BaseFactory;
import systems.comodal.hash.Sha3_256;

public final class Sha3_256Factory implements BaseFactory<Sha3_256> {

  @Override
  public Sha3_256 overlay(final byte[] digest) {
    return new DiscreteSha3_256(digest);
  }

  @Override
  public Sha3_256 overlay(final byte[] digest, final int offset) {
    return new BigEndianOffsetSha3_256(digest, offset);
  }

  @Override
  public Sha3_256 reverseOverlay(final byte[] digest, final int offset) {
    return new LittleEndianOffsetSha3_256(digest, offset);
  }
}
