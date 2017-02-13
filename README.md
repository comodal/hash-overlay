# hash-overlay [![Build Status](https://travis-ci.org/comodal/hash-overlay.svg)](https://travis-ci.org/comodal/hash-overlay) [![JCenter](https://api.bintray.com/packages/comodal/libraries/hash-overlay/images/download.svg) ](https://bintray.com/comodal/libraries/hash-overlay/_latestVersion) [![License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat) ](LICENSE) [![codecov](https://codecov.io/gh/comodal/hash-overlay/branch/master/graph/badge.svg)](https://codecov.io/gh/comodal/hash-overlay)

> Efficient binary overlay classes for message digests.

## Supported Message Digest Algorithms

All fixed length message digest algorithms provided by Oracle JDK 9 and the latest BouncyCastleProvider are available.  See the [root source directory](src/systems.comodal.hash_overlay/java/systems/comodal/hash) for a quick look of all available algorithms.

## Project Goals

* Fast hash look-ups (hashCode & equals).
* Minimize memory usage.
  * Best case, each Hash Object only holds a reference to the digest byte array.  Worst case, an additional offset integer as well.
  * Support overlaying of existing byte arrays which already contain digest data to avoid duplication.
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
dependencies {
    compile 'org.bouncycastle:bcprov-jdk15on:+'
}
```

```java
Security.addProvider(new BouncyCastleProvider());

BLAKE2B160 digest = BLAKE2B160.FACTORY.hash(msg);

```

## Generating Source

All of the algorithm specific code is generated using [Mustache](https://github.com/spullara/mustache.java) templates.  Upon generation, any existing generated code is deleted, and new implementation code is generated for every message digest algorithm found at runtime.

```bash
./gradlew generateSrc
```