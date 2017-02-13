package systems.comodal.hash;

public interface Sha3_256 extends Hash {

  @Override
  default HashFactory getFactory() {
    return DigestAlgo.SHA3_256;
  }

  @Override
  default int getDigestLength() {
    return DigestAlgo.SHA3_256.digestLength;
  }
}