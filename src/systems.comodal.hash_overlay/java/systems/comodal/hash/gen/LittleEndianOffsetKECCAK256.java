package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK256;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.LittleEndianOffsetHash;

public final class LittleEndianOffsetKECCAK256 extends LittleEndianOffsetHash implements KECCAK256 {

  public LittleEndianOffsetKECCAK256(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<KECCAK256> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK256
        && ((Hash) other).digestEqualsReverse(data, offset);
  }
}