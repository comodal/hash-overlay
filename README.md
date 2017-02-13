# hash-overlay [![Build Status](https://travis-ci.org/comodal/hash-overlay.svg)](https://travis-ci.org/comodal/hash-overlay) [![JCenter](https://api.bintray.com/packages/comodal/libraries/hash-overlay/images/download.svg) ](https://bintray.com/comodal/libraries/hash-overlay/_latestVersion) [![License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat) ](LICENSE) [![codecov](https://codecov.io/gh/comodal/hash-overlay/branch/master/graph/badge.svg)](https://codecov.io/gh/comodal/hash-overlay)

> Memory & speed efficient binary overlay classes for message digests.

## Project Goals

* Fast hash look-ups (hashCode & equals).
* Minimize memory usage.
  * Best case, each Hash Object only holds a reference to the digest byte array.  Worst case, an offset integer as well.
  * Support overlaying existing byte arrays which already contain digest data to avoid the need to duplicate data.
* Make the handling of message digests as convenient as possible without sacrificing performance.

```java
byte[] data = "Hello World".getBytes(StandardCharsets.UTF_8);
HashFactory<Hash> factory = DigestAlgo.SHA3_256;
Hash digest = factory.hash(data);

Map<Hash, byte[]> cache = new HashMap<>();
cache.put(digest, data);

byte[] nested = new byte[1024];
int offset = 42;
digest.copyHashTo(nested, offset);
Hash overlay = factory.overlay(nested, offset);

System.out.println(new String(cache.get(overlay)));
```
