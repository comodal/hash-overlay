# hash-overlay [![Build Status](https://travis-ci.org/comodal/hash-overlay.svg)](https://travis-ci.org/comodal/hash-overlay) [![JCenter](https://api.bintray.com/packages/comodal/libraries/hash-overlay/images/download.svg) ](https://bintray.com/comodal/libraries/hash-overlay/_latestVersion) [![License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat) ](LICENSE) [![codecov](https://codecov.io/gh/comodal/hash-overlay/branch/master/graph/badge.svg)](https://codecov.io/gh/comodal/hash-overlay)

> Binary overlay classes for message digests.

*Build Note:* [Gradle is currently broken for Java 9 past build 143](https://github.com/gradle/gradle/issues/1095). TravisCI builds will be broken until Gradle fixes their [Java 9 issues](https://github.com/gradle/gradle/issues?utf8=%E2%9C%93&q=is%3Aissue%20is%3Aopen%20Java%209) :/ Developing locally with JDK 9-ea build 143 works fine.

## Supported Message Digest Algorithms

All fixed-length message digest algorithms from providers `SUN v9` and `Bouncy Castle v1.56` are available.  See the [root source directory](src/systems.comodal.hash_overlay/java/systems/comodal/hash) for a quick look of all available algorithms.

## Project Goals

* Fast hash look-ups (hashCode & equals).
* Minimize memory usage.
  * Each Hash Object only holds a final reference to the digest byte array, resulting in a 16 byte Object.  Only if necessary, a final offset int as well, resulting in a 24 byte Object.  Compared to being lazy and wrapping it in a ByteBuffer (48 byte Object).
  * Support overlaying of big or little endian byte arrays to to prevent the need to copy or reverse arrays.
  * Every effort will be made to convert existing classes to Java 10 Value Types.  This will probably mean the removal of base classes and generating the same code for every value type.  Default interface method implementations are avoided because of potential Value Type performance implications.
* Make the handling of message digests as convenient as possible without sacrificing performance.

## Example Usage

```java
byte[] message = "Hello World".getBytes(StandardCharsets.UTF_8);

HashFactory<SHA3_256> factory = SHA3_256.FACTORY;
SHA3_256 digest = factory.hash(message);

Map<Hash, byte[]> cache = new HashMap<>();
cache.put(digest, data);

byte[] nested = new byte[1024];
int offset = 42;
digest.copyHashTo(nested, offset);
SHA3_256 overlay = factory.overlay(nested, offset);

System.out.println(new String(cache.get(overlay)));
```

## Using [Bouncy Castle](https://www.bouncycastle.org/) Provided Algorithms

```groovy
// build.gradle
dependencies {
    compile 'org.bouncycastle:bcprov-jdk15on:+'
}
```

```java
// Once at startup
Security.addProvider(new BouncyCastleProvider());
// ...
BLAKE2B160 digest = BLAKE2B160.FACTORY.hash(msg);

```

## [Multihash](https://github.com/multiformats/multihash) Support

* Decode Multihash encoded digests.
* Lookup up HashFactories by Multihash function codes.
* Function code getters.
* [Multiformat Unsigned VarInt](https://github.com/multiformats/unsigned-varint) encoding & decoding.

See [HashFactoryFnCodeFactory.java](src/systems.comodal.hash_overlay/java/systems/comodal/hash/multihash/HashFactoryFnCodeFactory.java#L15) for a list of supported Multihash function codes.

See [MultiHashTest.java](src/test/java/systems/comodal/hash/MultiHashTest.java#L17) for more complete usage examples.

```java
// Decoding
byte[] multiHashEncoded = ... // <varint fn code><varint digest length><digest>

// Copies the digest to a new byte array with the exact digest length.
Hash hash = MultiHash.createCopy(multiHashEncoded);

// Overlay's the existing byte array.
Hash overlay = MultiHash.createOverlay(multiHashEncoded);
```

```java
// Encoding
Hash hash = ...
byte[] prefix = hash.getFactory().getMultiHashPrefix();
byte[] multiHashEncoded = new byte[prefix.length + hash.getDigestLength()];
System.arraycopy(prefix, 0, multiHashEncoded, 0, prefix.length);
hash.copyTo(multiHash, prefix.length);
```

```java 
// VarInt Encoding/Decoding
int val = 128;
byte[] varInt = MultiHash.encodeVarInt(val);
long unsignedInt = MultiHash.decodeVarInt(varInt);
System.out.println(unsignedInt == val);
```

```java
// HashFactory Lookup
int fnCode = 0xB220;
HashFactory<? extends Hash> hashFactory = MultiHash.getHashFactory(fnCode);
// Blake2b
System.out.println(hashFactory.getMessageDigest().getAlgorithm());

byte[] varIntFnCode = MultiHash.encodeVarInt(fnCode);
hashFactory = MultiHash.getHashFactory(varIntFnCode);
// Blake2b
System.out.println(hashFactory.getMessageDigest().getAlgorithm());
```

## Generating Source

All of the algorithm specific code is generated using [Mustache](https://github.com/spullara/mustache.java) templates.  Upon generation, any existing generated code is deleted, and new implementation code is created for every message digest algorithm found at runtime.

See the [mustache module](src/mustache) for the source that drives the code generation as well as the template resource files.

```bash
./gradlew generateSrc
```
