package systems.comodal.hash.benchmark;

import java.nio.ByteBuffer;
import org.github.jamm.MemoryMeter;
import systems.comodal.hash.SHA256;

public class MemoryMeasurements {


  public static void main(final String[] args) {
    // -javaagent:/Users/james/git/hash-overlay/libs/jamm-0.3.2-SNAPSHOT.jar
    final MemoryMeter meter = new MemoryMeter().enableDebug();
    System.out.format("ByteBuffer: %d bytes.%n", meter.measure(ByteBuffer.wrap(new byte[0])));
    System.out.format("Hash: %d bytes.%n", meter.measure(SHA256.FACTORY.overlay(new byte[0])));
    System.out.format("OffsetHash: %d bytes.%n", meter.measure(SHA256.FACTORY.overlay(new byte[0], 0)));
  }
}
