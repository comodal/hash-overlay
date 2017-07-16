package systems.comodal.hash.benchmark;

import java.security.Security;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
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
import systems.comodal.hash.SHA256;
import systems.comodal.hash.SHA512_256;
import systems.comodal.hash.SHA3_256;
import systems.comodal.hash.GOST3411;
import systems.comodal.hash.GOST3411_2012_256;
import systems.comodal.hash.KECCAK256;
import systems.comodal.hash.RIPEMD256;
import systems.comodal.hash.Skein256_256;
import systems.comodal.hash.Skein512_256;
import systems.comodal.hash.SM3;
import systems.comodal.hash.BLAKE2B256;
import systems.comodal.hash.api.Hash;

@State(Scope.Benchmark)
@Threads(Threads.MAX)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 7)
@Measurement(iterations = 10)
public class Digest32ByteBench {

  private static final int NUM_MESSAGES = 2 << 18;
  private static final int MASK = NUM_MESSAGES - 1;

  static {
    Security.addProvider(new BouncyCastleProvider());
  }

  @Param({"32"})
  String msgLength;
  byte[][] messages;

  @Setup
  public void setup() {
    messages = new byte[NUM_MESSAGES][Integer.parseInt(msgLength)];
    final ThreadLocalRandom rand = ThreadLocalRandom.current();
    for (int i = 0; i < NUM_MESSAGES; i++) {
      rand.nextBytes(messages[i]);
    }
  }

  @Benchmark
  public Hash SHA256__SUN9(final ThreadState threadState) {
    return SHA256.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash SHA512_256__SUN9(final ThreadState threadState) {
    return SHA512_256.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash SHA3_256__SUN9(final ThreadState threadState) {
    return SHA3_256.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash GOST3411__BC1_57(final ThreadState threadState) {
    return GOST3411.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash GOST3411_2012_256__BC1_57(final ThreadState threadState) {
    return GOST3411_2012_256.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash KECCAK256__BC1_57(final ThreadState threadState) {
    return KECCAK256.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash RIPEMD256__BC1_57(final ThreadState threadState) {
    return RIPEMD256.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash Skein256_256__BC1_57(final ThreadState threadState) {
    return Skein256_256.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash Skein512_256__BC1_57(final ThreadState threadState) {
    return Skein512_256.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash SM3__BC1_57(final ThreadState threadState) {
    return SM3.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash BLAKE2B256__BC1_57(final ThreadState threadState) {
    return BLAKE2B256.FACTORY.hash(messages[threadState.next()]);
  }

  @State(Scope.Thread)
  public static class ThreadState {

    int index = ThreadLocalRandom.current().nextInt(NUM_MESSAGES);

    int next() {
      return index++ & MASK;
    }
  }
}
