# hash-overlay [![Build Status](https://travis-ci.org/comodal/hash-overlay.svg)](https://travis-ci.org/comodal/hash-overlay) [![JCenter](https://api.bintray.com/packages/comodal/libraries/hash-overlay/images/download.svg) ](https://bintray.com/comodal/libraries/hash-overlay/_latestVersion) [![License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat) ](LICENSE) [![codecov](https://codecov.io/gh/comodal/hash-overlay/branch/master/graph/badge.svg)](https://codecov.io/gh/comodal/hash-overlay)

> Binary overlay classes for message digests.

## Supported Message Digest Algorithms

All fixed-length message digest algorithms from providers `SUN v9` and `Bouncy Castle v1.56` are available.  See the [root source directory](src/systems.comodal.hash_overlay/java/systems/comodal/hash) for a quick look of all available algorithms.

## Project Goals

* Fast hash look-ups (hashCode & equals).
* Minimize memory usage.
  * Each Hash Object only holds a final reference to the digest byte array, and, if necessary, a final offset int.
  * Support overlaying of big or little endian byte arrays to to prevent the need to copy or reverse arrays.
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
// ...
BLAKE2B160 digest = BLAKE2B160.FACTORY.hash(msg);

```

## Generating Source

All of the algorithm specific code is generated using [Mustache](https://github.com/spullara/mustache.java) templates.  Upon generation, any existing generated code is deleted, and new implementation code is created for every message digest algorithm found at runtime.

See the [mustache module](src/mustache) for the source that drives the code generation as well as the template resource files.

```bash
./gradlew generateSrc
```
