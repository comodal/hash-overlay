package systems.comodal.hash.api;

public interface HashedData extends Hash {

  byte[] getBackingHashedData();

  int getHashedDataOffset();

  int getHashedDataLength();

  boolean isHashedDataDiscrete();

  byte[] getBackingData();

  int getDataOffset();

  int getDataLength();

  boolean isDataDiscrete();
}
