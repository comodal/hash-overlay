package systems.comodal.hash.gen;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.KECCAK224;
import systems.comodal.hash.api.HashFactory;
import systems.comodal.hash.base.BigEndianOffsetHash;

public final class BigEndianOffsetKECCAK224 extends BigEndianOffsetHash implements KECCAK224 {

  public BigEndianOffsetKECCAK224(final byte[] data, final int offset) {
    super(data, offset);
  }

  @Override
  public HashFactory<KECCAK224> getFactory() {
    return FACTORY;
  }

  @Override
  public boolean equals(final Object other) {
    return this == other || other != null && other instanceof KECCAK224
        && ((Hash) other).digestEquals(data, offset);
  }
}