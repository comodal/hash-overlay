package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.MD5;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetMD5 extends LittleEndianOffsetHash implements MD5 {

  public LittleEndianOffsetMD5(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<MD5> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof MD5
        && ((Hash) other).equalsReverse(data, offset);
  }
}