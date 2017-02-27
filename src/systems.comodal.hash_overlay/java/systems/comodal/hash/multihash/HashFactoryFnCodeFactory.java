package systems.comodal.hash.multihash;

import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

final class HashFactoryFnCodeFactory {

  private HashFactoryFnCodeFactory() {
  }

  static HashFactory<? extends Hash> fromFnCode(final long fnCode) {
    if (fnCode > Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Unknown Multihash fn code: " + fnCode);
    }
    switch ((int) fnCode) {
      case 0x11:
        return systems.comodal.hash.SHA1.FACTORY;
      case 0x12:
        return systems.comodal.hash.SHA256.FACTORY;
      case 0x13:
        return systems.comodal.hash.SHA512.FACTORY;
      case 0x14:
        return systems.comodal.hash.SHA3_512.FACTORY;
      case 0x15:
        return systems.comodal.hash.SHA3_384.FACTORY;
      case 0x16:
        return systems.comodal.hash.SHA3_256.FACTORY;
      case 0x17:
        return systems.comodal.hash.SHA3_224.FACTORY;
      case 0x1A:
        return systems.comodal.hash.KECCAK224.FACTORY;
      case 0x1B:
        return systems.comodal.hash.KECCAK256.FACTORY;
      case 0x1C:
        return systems.comodal.hash.KECCAK384.FACTORY;
      case 0x1D:
        return systems.comodal.hash.KECCAK512.FACTORY;
      case 0xB214:
        return systems.comodal.hash.BLAKE2B160.FACTORY;
      case 0xB220:
        return systems.comodal.hash.BLAKE2B256.FACTORY;
      case 0xB230:
        return systems.comodal.hash.BLAKE2B384.FACTORY;
      case 0xB240:
        return systems.comodal.hash.BLAKE2B512.FACTORY;
      default:
        throw new IllegalArgumentException("Unknown Multihash fn code: " + fnCode);
    }
  }
}
