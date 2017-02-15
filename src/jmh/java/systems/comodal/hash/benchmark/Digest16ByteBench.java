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
import systems.comodal.hash.MD2;
import systems.comodal.hash.MD5;
import systems.comodal.hash.MD4;
import systems.comodal.hash.RIPEMD128;
import systems.comodal.hash.Skein256_128;
import systems.comodal.hash.Skein512_128;
import systems.comodal.hash.api.Hash;

@State(Scope.Benchmark)
@Threads(Threads.MAX)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 7)
@Measurement(iterations = 10)
public class Digest16ByteBench {

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
  public Hash MD2__SUN9(final ThreadState threadState) {
    return MD2.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash MD5__SUN9(final ThreadState threadState) {
    return MD5.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash MD4__BC1_56(final ThreadState threadState) {
    return MD4.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash RIPEMD128__BC1_56(final ThreadState threadState) {
    return RIPEMD128.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash Skein256_128__BC1_56(final ThreadState threadState) {
    return Skein256_128.FACTORY.hash(messages[threadState.next()]);
  }

  @Benchmark
  public Hash Skein512_128__BC1_56(final ThreadState threadState) {
    return Skein512_128.FACTORY.hash(messages[threadState.next()]);
  }

  @State(Scope.Thread)
  public static class ThreadState {

    int index = ThreadLocalRandom.current().nextInt(NUM_MESSAGES);

    int next() {
      return index++ & MASK;
    }
  }
}
