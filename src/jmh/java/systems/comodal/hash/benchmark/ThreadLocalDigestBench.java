package systems.comodal.hash.benchmark;

import java.security.MessageDigest;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
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
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

@State(Scope.Benchmark)
@Threads(1)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 7)
@Measurement(iterations = 10)
public class ThreadLocalDigestBench {

  private static final int NUM_MESSAGES = 2 << 18;
  private static final int MASK = NUM_MESSAGES - 1;
  private static final HashFactory<? extends Hash> hashFactory = SHA256.FACTORY;
  private static final MessageDigest messageDigest = hashFactory.getMessageDigest();

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
  public byte[] threadLocal(final ThreadState threadState) {
    return hashFactory.getMessageDigest().digest(messages[threadState.next()]);
  }

  @Benchmark
  public byte[] singleton(final ThreadState threadState) {
    return messageDigest.digest(messages[threadState.next()]);
  }

  @State(Scope.Thread)
  public static class ThreadState {

    int index = ThreadLocalRandom.current().nextInt(NUM_MESSAGES);

    int next() {
      return index++ & MASK;
    }
  }
}
