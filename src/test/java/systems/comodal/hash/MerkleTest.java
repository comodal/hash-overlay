package systems.comodal.hash;

import static org.bouncycastle.util.encoders.Hex.decode;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import systems.comodal.hash.api.Hash;
import systems.comodal.hash.api.HashFactory;

public class MerkleTest {

  private final HashFactory<SHA256> factory = SHA256.FACTORY;

  @Test
  public void testEven() {
    final Hash[] hashes = new Hash[]{
        factory.overlay(decode("8c14f0db3df150123e6f3dbbf30f8b955a8249b62ac1d1ff16284aefa3d06d87")),
        factory.overlay(decode("fff2525b8931402dd09222c50775608f75787bd2b87e56995a7bdd30f79702c4")),
        factory.overlay(decode("6359f0868171b1d194cbee1af2f16ea598ae8fad666d9b012c8ed2b79a236ec4")),
        factory.overlay(decode("e9a66845e05d5abc0ad04ec80f774a7e585c6e8db975962d069a522137b80c1d"))
    };
    assertEquals(
        factory.overlay(decode("f3e94742aca4b5ef85488dc37c06c3282295ffec960994b2c0d5ac2a25a95766")),
        factory.merkle(hashes));
  }

  @Test
  public void testOdd() {
    final Hash[] hashes = new Hash[]{
        factory.overlay(decode("ef1d870d24c85b89d92ad50f4631026f585d6a34e972eaf427475e5d60acf3a3")),
        factory.overlay(decode("f9fc751cb7dc372406a9f8d738d5e6f8f63bab71986a39cf36ee70ee17036d07")),
        factory.overlay(decode("db60fb93d736894ed0b86cb92548920a3fe8310dd19b0da7ad97e48725e1e12e")),
        factory.overlay(decode("220ebc64e21abece964927322cba69180ed853bb187fbc6923bac7d010b9d87a")),
        factory.overlay(decode("71b3dbaca67e9f9189dad3617138c19725ab541ef0b49c05a94913e9f28e3f4e")),
        factory.overlay(decode("fe305e1ed08212d76161d853222048eea1f34af42ea0e197896a269fbf8dc2e0")),
        factory.overlay(decode("21d2eb195736af2a40d42107e6abd59c97eb6cffd4a5a7a7709e86590ae61987")),
        factory.overlay(decode("dd1fd2a6fc16404faf339881a90adbde7f4f728691ac62e8f168809cdfae1053")),
        factory.overlay(decode("74d681e0e03bafa802c8aa084379aa98d9fcd632ddc2ed9782b586ec87451f20"))
    };
    assertEquals(
        factory.overlay(decode("2fda58e5959b0ee53c5253da9b9f3c0c739422ae04946966991cf55895287552")),
        factory.merkle(hashes));
  }
}
