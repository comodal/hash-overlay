package systems.comodal.hash.benchmark;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import systems.comodal.hash.BLAKE2B160;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

@State(Scope.Benchmark)
@Threads(Threads.MAX)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 7)
@Measurement(iterations = 10)
public class WrapGetBench {

  private static final int NUM_HASHES = 2 << 19;
  private static final int MASK = NUM_HASHES - 1;
  private static final HashFactory<BLAKE2B160> HASH_FACTORY = BLAKE2B160.FACTORY;
  private static final int DIGEST_LENGTH = HASH_FACTORY.getDigestLength();
  private static final byte[][] DIGESTS = new byte[NUM_HASHES][DIGEST_LENGTH];
  private static final int OFFSET = (DIGEST_LENGTH / 2);
  private static final int OFFSET_ARRAY_LENGTH = DIGEST_LENGTH * 2;
  private static final byte[][] OFFSET_DIGESTS = new byte[NUM_HASHES][OFFSET_ARRAY_LENGTH];
  private static final byte[][] REVERSE_OFFSET_DIGESTS = new byte[NUM_HASHES][OFFSET_ARRAY_LENGTH];

  static {
    System.out.format("Generating %d arrays %d bytes in length.%n", DIGESTS.length, DIGEST_LENGTH);
    final ThreadLocalRandom rand = ThreadLocalRandom.current();
    for (int i = 0; i < NUM_HASHES; i++) {
      rand.nextBytes(DIGESTS[i]);
      System.arraycopy(DIGESTS[i], 0, OFFSET_DIGESTS[i], OFFSET, DIGEST_LENGTH);
      System.arraycopy(DIGESTS[i], 0, REVERSE_OFFSET_DIGESTS[i], 0, DIGEST_LENGTH);
      Hash.reverse(REVERSE_OFFSET_DIGESTS[i]);
    }
  }

  @Param({
      "Hash",
      "ByteBuffer",
      "HashOffset",
      "ByteBufferOffset",
      "HashReverseOffset",
      "ByteBufferReverseOffset"
  })
  String keyStrategy;
  private Map<Object, Boolean> map;
  private Function<ThreadState, Object> keySupplier;

  @Setup
  public void setup() {
    map = new ConcurrentHashMap<>(DIGESTS.length);

    switch (keyStrategy) {
      case "Hash":
        for (byte[] digest : DIGESTS) {
          map.put(HASH_FACTORY.overlay(digest), Boolean.TRUE);
        }
        keySupplier = threadState -> HASH_FACTORY.overlay(threadState.next());
        break;
      case "ByteBuffer":
        for (byte[] digest : DIGESTS) {
          map.put(ByteBuffer.wrap(digest), Boolean.TRUE);
        }
        keySupplier = threadState -> ByteBuffer.wrap(threadState.next());
        break;
      case "HashOffset":
        for (byte[] digest : DIGESTS) {
          map.put(HASH_FACTORY.overlay(digest), Boolean.TRUE);
        }
        keySupplier = threadState -> HASH_FACTORY.overlay(threadState.nextOffset(), OFFSET);
        break;
      case "ByteBufferOffset":
        for (byte[] digest : DIGESTS) {
          map.put(ByteBuffer.wrap(digest), Boolean.TRUE);
        }
        keySupplier = threadState -> ByteBuffer
            .wrap(threadState.nextOffset(), OFFSET, DIGEST_LENGTH);
        break;
      case "HashReverseOffset":
        for (byte[] digest : DIGESTS) {
          map.put(HASH_FACTORY.overlay(digest), Boolean.TRUE);
        }
        keySupplier = threadState -> HASH_FACTORY
            .reverseOverlay(threadState.nextReverseOffset(), DIGEST_LENGTH);
        break;
      case "ByteBufferReverseOffset":
        for (byte[] digest : DIGESTS) {
          map.put(ByteBuffer.wrap(digest), Boolean.TRUE);
        }
        keySupplier = threadState -> {
          final byte[] copy = new byte[DIGEST_LENGTH];
          System.arraycopy(threadState.nextReverseOffset(), DIGEST_LENGTH, copy, 0, DIGEST_LENGTH);
          Hash.reverse(copy);
          return ByteBuffer.wrap(copy, 0, DIGEST_LENGTH);
        };
        break;
      default:
        throw new IllegalStateException("Unknown key strategy " + keyStrategy);
    }
  }

  @Benchmark
  public boolean wrapAndGet(final ThreadState threadState) {
    return map.get(keySupplier.apply(threadState)).booleanValue();
  }

  @State(Scope.Thread)
  public static class ThreadState {

    int index = ThreadLocalRandom.current().nextInt(DIGESTS.length);

    byte[] next() {
      return DIGESTS[index++ & MASK];
    }

    byte[] nextOffset() {
      return OFFSET_DIGESTS[index++ & MASK];
    }

    byte[] nextReverseOffset() {
      return REVERSE_OFFSET_DIGESTS[index++ & MASK];
    }
  }
}
