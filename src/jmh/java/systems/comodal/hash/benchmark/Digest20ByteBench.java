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
import systems.comodal.hash.SHA;
import systems.comodal.hash.SHA1;
import systems.comodal.hash.RIPEMD160;
import systems.comodal.hash.Skein256_160;
import systems.comodal.hash.Skein512_160;
import systems.comodal.hash.BLAKE2B160;
import systems.comodal.hash.api.Hash;

@State(Scope.Benchmark)
@Threads(Threads.MAX)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 7)
@Measurement(iterations = 10)
public class Digest20ByteBench {

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
  public Hash SHA__SUN9(final ThreadState threadState) {
    return SHA.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash SHA1__SUN9(final ThreadState threadState) {
    return SHA1.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash RIPEMD160__BC1_57(final ThreadState threadState) {
    return RIPEMD160.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash Skein256_160__BC1_57(final ThreadState threadState) {
    return Skein256_160.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash Skein512_160__BC1_57(final ThreadState threadState) {
    return Skein512_160.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash BLAKE2B160__BC1_57(final ThreadState threadState) {
    return BLAKE2B160.FACTORY.hash(messages[threadState.next()]);
  }

  @State(Scope.Thread)
  public static class ThreadState {

    int index = ThreadLocalRandom.current().nextInt(NUM_MESSAGES);

    int next() {
      return index++ & MASK;
    }
  }
}
