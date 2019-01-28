package network.marmoj;

import static org.web3j.utils.Numeric.hexStringToByteArray;
import static org.web3j.utils.Numeric.toHexString;

import java.math.BigInteger;
import java.util.Arrays;
import network.marmoj.builder.IntentBuilder;
import network.marmoj.builder.SignedIntentBuilder;
import network.marmoj.config.Config;
import network.marmoj.model.IntentAction;
import network.marmoj.model.IntentWallet;
import network.marmoj.model.data.ERC20;
import network.marmoj.model.data.ETH;
import network.marmoj.model.data.ISendEth;
import org.junit.Assert;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

public class MarmoTest {

  private static final String[] privs = {
      "0x62d29230c55255d404f85cf45d2db438911a8e8c76b9e917656fdbd8c4adccf4",
      "0x5ef1dbf8ef171b33cd72a5d11b713442dcd2c70695753a0f6df9b38136e08d54",
      "0x6306c990056a965674edf80c7e1518d1c337abe005ffd7dcd17b25a2db0dfb2f",
      "0xadfc814c0e30d88889a5cf3701e8da4ea65fc15111f54591e6f0ee4aa129f40f",
      "0x2a050363f79a7da50302c2ed81a141f4307d056846339183c671d8defa10db33",
      "0x6de344483ec377e3262437805e3e9f290b1094d7c19bab52eca42bb471edc81a",
      "0x871cbb62ecf06d97185ca70e1722e51684db71066f43c672b6589d47c15d9cb3",
      "0x68159b0ce11c69e75aaa79286f4c6f9e11523f4c12631e608e6a6d60d57dbd94",
      "0x60b51acb27b07e5f8000ad8451469d1326d10357cad955ec4f5d5537ede0e9d8",
      "0x3a423f1c02a85be8641f67e36d91ae4089766ceb18bd7308c2e845d8e90fa705",
  };

  @Test
  public void testGenerateIntentIdSendEth() {

    ISendEth sendEth = new ETH();
    IntentAction intentAction = sendEth
        .send("0x009ab4de1234c7066197d6ed75743add3576591f", BigInteger.ONE);
    Config config = Config.newInstance(
        "0xe814f48c2eaf753ae51c7c807e2b1736700126c58af556d78c7c6158d201a125",
        "0x4E0B13eDeE810702884b72DBE018579Cb2e4C6fA"
    );
    IntentWallet intentWallet = new IntentWallet(privs[1], config);

    Intent intent = IntentBuilder.anIntent()
        .withSalt(hexStringToByteArray(
            "0x0000000000000000000000000000000000000000000000000000000000000000"))
        .withExpiration(BigInteger.TEN.pow(24))
        .withMinGasLimit(BigInteger.ZERO)
        .withMaxGasPrice(BigInteger.TEN.pow(32))
        .withIntentAction(intentAction)
        .build();

    SignedIntent signedIntent = SignedIntentBuilder.aSignedIntent()
        .withIntent(intent)
        .withWallet(intentWallet)
        .build();

    Assert.assertEquals(toHexString(signedIntent.getId()),
        "0xa6daa52099d4083291c39a4beb2579dbfda6d24393c5e49f2549f08e37739b74");
  }

  @Test
  public void testGenerateIntentIdSendTokens() {

    ERC20 erc20 = new ERC20("0x6B0F919A5d450Fa5e6283Ff6178dC1FCd195FD2A");
    IntentAction intentAction = erc20.transfer(
        new Address("0x009ab4de1234c7066197d6ed75743add3576591f"),
        new Uint256(4)
    );
    Config config = Config.newInstance(
        "0xe814f48c2eaf753ae51c7c807e2b1736700126c58af556d78c7c6158d201a125",
        "0x4E0B13eDeE810702884b72DBE018579Cb2e4C6fA"
    );
    IntentWallet intentWallet = new IntentWallet(privs[1], config);

    Intent intent = IntentBuilder.anIntent()
        .withSalt(hexStringToByteArray(
            "0x0000000000000000000000000000000000000000000000000000000000000000"))
        .withExpiration(BigInteger.valueOf(1548030494))
        .withMinGasLimit(BigInteger.ZERO)
        .withMaxGasPrice(BigInteger.TEN.pow(32))
        .withIntentAction(intentAction)
        .build();

    SignedIntent signedIntent = SignedIntentBuilder.aSignedIntent()
        .withIntent(intent)
        .withWallet(intentWallet)
        .build();

    Assert.assertEquals(toHexString(signedIntent.getId()),
        "0xe34f44ab2514803ba5f1a4766f5fe1d6d012a9599c8e13843962366f04427198");
  }

  @Test
  public void testGenerateIntentIdSendEthWithDependencies() {

    ISendEth sendEth = new ETH();
    IntentAction intentAction = sendEth
        .send("0x008d03067bcb29c5b35de2ee4a2fb88b965edf61", BigInteger.valueOf(2));
    Config config = Config.newInstance(
        "0xe814f48c2eaf753ae51c7c807e2b1736700126c58af556d78c7c6158d201a125",
        "0x4E0B13eDeE810702884b72DBE018579Cb2e4C6fA"
    );
    IntentWallet intentWallet = new IntentWallet(privs[1], config);

    Intent intent = IntentBuilder.anIntent()
        .withExpiration(BigInteger.valueOf(1548069482))
        .withMaxGasPrice(BigInteger.TEN.pow(32))
        .withIntentAction(intentAction)
        .withDependencies(Arrays.asList(hexStringToByteArray(
            "0xa6daa52099d4083291c39a4beb2579dbfda6d24393c5e49f2549f08e37739b74")))
        .build();

    SignedIntent signedIntent = SignedIntentBuilder.aSignedIntent()
        .withIntent(intent)
        .withWallet(intentWallet)
        .build();

    Assert.assertEquals(toHexString(signedIntent.getId()),
        "0x2cd48b6d072d54707850d17ca199e5c3ed8ecc3d626c78c872ac2a9e9b5f31ec");
  }

  @Test
  public void testSign() {

    ISendEth sendEth = new ETH();
    IntentAction intentAction = sendEth
        .send("0x008d03067bcb29c5b35de2ee4a2fb88b965edf61", BigInteger.valueOf(2));
    Config config = Config.newInstance(
        "0xe814f48c2eaf753ae51c7c807e2b1736700126c58af556d78c7c6158d201a125",
        "0x4E0B13eDeE810702884b72DBE018579Cb2e4C6fA"
    );
    IntentWallet intentWallet = new IntentWallet(privs[1], config);

    Intent intent = IntentBuilder.anIntent()
        .withExpiration(BigInteger.valueOf(1548069482))
        .withMaxGasPrice(BigInteger.TEN.pow(32))
        .withIntentAction(intentAction)
        .withDependencies(Arrays.asList(hexStringToByteArray(
            "0xa6daa52099d4083291c39a4beb2579dbfda6d24393c5e49f2549f08e37739b74")))
        .build();

    SignedIntent signedIntent = SignedIntentBuilder.aSignedIntent()
        .withIntent(intent)
        .withWallet(intentWallet)
        .build();

    Assert.assertEquals(toHexString(signedIntent.getSignature().getR()),
        "0x29d321ce0d6d2f8a4070f4c54bf19917987d10aa7aff967eb70f995f45522ef5");
    Assert.assertEquals(toHexString(signedIntent.getSignature().getS()),
        "0x01ae6eedc4f5cf12518bcb7894ec0345fef8860c288e319bf6a71c38fa617c09");
    Assert.assertEquals(String.valueOf(signedIntent.getSignature().getV()), "28");


  }

}
