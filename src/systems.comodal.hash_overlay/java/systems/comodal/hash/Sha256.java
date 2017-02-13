package systems.comodal.hash;

public interface Sha256 extends Hash {

  @Override
  default HashFactory getFactory() {
    return DigestAlgo.SHA_256;
  }

  @Override
  default int getDigestLength() {
    return DigestAlgo.SHA_256.digestLength;
  }
}
