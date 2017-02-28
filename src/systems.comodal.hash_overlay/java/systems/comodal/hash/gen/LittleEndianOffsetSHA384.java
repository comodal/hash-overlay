package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.SHA384;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetSHA384 extends LittleEndianOffsetHash implements SHA384 {

  public LittleEndianOffsetSHA384(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<SHA384> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof SHA384
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}