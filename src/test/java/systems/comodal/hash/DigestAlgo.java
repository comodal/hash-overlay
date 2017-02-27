package systems.comodal.hash;

import java.security.MessageDigest;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

public enum DigestAlgo implements HashFactory<Hash> {

  BLAKE2B160(systems.comodal.hash.BLAKE2B160.FACTORY),
  BLAKE2B256(systems.comodal.hash.BLAKE2B256.FACTORY),
  BLAKE2B384(systems.comodal.hash.BLAKE2B384.FACTORY),
  BLAKE2B512(systems.comodal.hash.BLAKE2B512.FACTORY),
  GOST3411(systems.comodal.hash.GOST3411.FACTORY),
  GOST3411_2012_256(systems.comodal.hash.GOST3411_2012_256.FACTORY),
  GOST3411_2012_512(systems.comodal.hash.GOST3411_2012_512.FACTORY),
  KECCAK224(systems.comodal.hash.KECCAK224.FACTORY),
  KECCAK256(systems.comodal.hash.KECCAK256.FACTORY),
  KECCAK288(systems.comodal.hash.KECCAK288.FACTORY),
  KECCAK384(systems.comodal.hash.KECCAK384.FACTORY),
  KECCAK512(systems.comodal.hash.KECCAK512.FACTORY),
  MD2(systems.comodal.hash.MD2.FACTORY),
  MD4(systems.comodal.hash.MD4.FACTORY),
  MD5(systems.comodal.hash.MD5.FACTORY),
  RIPEMD128(systems.comodal.hash.RIPEMD128.FACTORY),
  RIPEMD160(systems.comodal.hash.RIPEMD160.FACTORY),
  RIPEMD256(systems.comodal.hash.RIPEMD256.FACTORY),
  RIPEMD320(systems.comodal.hash.RIPEMD320.FACTORY),
  SHA(systems.comodal.hash.SHA.FACTORY),
  SHA1(systems.comodal.hash.SHA1.FACTORY),
  SHA224(systems.comodal.hash.SHA224.FACTORY),
  SHA256(systems.comodal.hash.SHA256.FACTORY),
  SHA384(systems.comodal.hash.SHA384.FACTORY),
  SHA3_224(systems.comodal.hash.SHA3_224.FACTORY),
  SHA3_256(systems.comodal.hash.SHA3_256.FACTORY),
  SHA3_384(systems.comodal.hash.SHA3_384.FACTORY),
  SHA3_512(systems.comodal.hash.SHA3_512.FACTORY),
  SHA512(systems.comodal.hash.SHA512.FACTORY),
  SHA512_224(systems.comodal.hash.SHA512_224.FACTORY),
  SHA512_256(systems.comodal.hash.SHA512_256.FACTORY),
  SM3(systems.comodal.hash.SM3.FACTORY),
  Skein1024_1024(systems.comodal.hash.Skein1024_1024.FACTORY),
  Skein1024_384(systems.comodal.hash.Skein1024_384.FACTORY),
  Skein1024_512(systems.comodal.hash.Skein1024_512.FACTORY),
  Skein256_128(systems.comodal.hash.Skein256_128.FACTORY),
  Skein256_160(systems.comodal.hash.Skein256_160.FACTORY),
  Skein256_224(systems.comodal.hash.Skein256_224.FACTORY),
  Skein256_256(systems.comodal.hash.Skein256_256.FACTORY),
  Skein512_128(systems.comodal.hash.Skein512_128.FACTORY),
  Skein512_160(systems.comodal.hash.Skein512_160.FACTORY),
  Skein512_224(systems.comodal.hash.Skein512_224.FACTORY),
  Skein512_256(systems.comodal.hash.Skein512_256.FACTORY),
  Skein512_384(systems.comodal.hash.Skein512_384.FACTORY),
  Skein512_512(systems.comodal.hash.Skein512_512.FACTORY),
  TIGER(systems.comodal.hash.TIGER.FACTORY),
  WHIRLPOOL(systems.comodal.hash.WHIRLPOOL.FACTORY);

  private final HashFactory<? extends Hash> delegate;

  DigestAlgo(final HashFactory<? extends Hash> delegate) {
    this.delegate = delegate;
  }

  @Override
  public MessageDigest getMessageDigest() {
    return delegate.getMessageDigest();
  }

  @Override
  public int getDigestLength() {
    return delegate.getDigestLength();
  }

  @Override
  public long getMultiHashFnCode() {
    return delegate.getMultiHashFnCode();
  }

  @Override
  public Hash overlay(byte[] digest) {
    return delegate.overlay(digest);
  }

  @Override
  public Hash overlay(byte[] digest, int offset) {
    return delegate.overlay(digest, offset);
  }

  @Override
  public Hash reverseOverlay(byte[] digest, int offset) {
    return delegate.reverseOverlay(digest, offset);
  }
}