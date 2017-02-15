package systems.comodal.hash.benchmark;

import java.nio.ByteBuffer;
import org.github.jamm.MemoryMeter;
import systems.comodal.hash.SHA256;

public final class MemoryMeasurements {

  public static void main(final String[] args) {
    // -javaagent:/Users/james/git/hash-overlay/libs/jamm-0.3.2-SNAPSHOT.jar
    final MemoryMeter meter = new MemoryMeter().enableDebug();
    meter.measureDeep(ByteBuffer.wrap(new byte[0]));
    meter.measureDeep(SHA256.FACTORY.overlay(new byte[0]));
    meter.measureDeep(SHA256.FACTORY.overlay(new byte[0], 0));
  }
}
