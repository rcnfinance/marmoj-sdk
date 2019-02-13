package network.marmoj;

import static org.web3j.utils.Numeric.toHexString;

import java.math.BigInteger;
import java.util.Arrays;
import network.marmoj.builder.IntentBuilder;
import network.marmoj.builder.SignedIntentBuilder;
import network.marmoj.config.Config;
import network.marmoj.model.IntentAction;
import network.marmoj.model.IntentDependency;
import network.marmoj.model.Wallet;
import network.marmoj.model.data.ERC20;
import network.marmoj.model.data.ETH;
import network.marmoj.model.data.IERC20;
import network.marmoj.model.data.ISendEth;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

public class MarmoTest {

  private IERC20 erc20;
  private Wallet wallet;
  private Config config;

  @Before
  public void init() {

    this.config = new Config(
        "0xd586145101ec2c83174d91f2dd8df4b0cdb335f8f77935be590114916b535944",
        "0x68EA020095c1B3E58687cfA8eC2D631137Db28d7",
        "0x4E0B13eDeE810702884b72DBE018579Cb2e4C6fA",
        "0x6B0F919A5d450Fa5e6283Ff6178dC1FCd195FD2A",
        999
    );

    String tokenContractAddress = "0x6Eb29e4Dffcbe467b755DCBa6fDdfA91F6f747e1";
    this.wallet = new Wallet("0x5ef1dbf8ef171b33cd72a5d11b713442dcd2c70695753a0f6df9b38136e08d54", this.config);
    this.erc20 = new ERC20(tokenContractAddress);

  }

  @Test
  public void testTransfer() {


    IntentAction intentAction = this.erc20.transfer(new Address("0x009ab4de1234c7066197d6ed75743add3576591f"), new Uint256(4));
    Intent intent = IntentBuilder.anIntent()
        .withIntentAction(intentAction)
        .withExpiration(BigInteger.TEN.pow(24))
        .withMaxGasLimit(BigInteger.ZERO)
        .withMaxGasPrice(BigInteger.TEN.pow(32))
        .build();

    SignedIntent signedIntent = SignedIntentBuilder.aSignedIntent()
        .withIntent(intent)
        .withWallet(wallet)
        .build();

    Assert.assertEquals(toHexString(signedIntent.getId()),
        "0x46ba292cc630f19a56e0f531f2ec2427180d13936f2c8e7ff899af8331543ff7");

  }

  @Test
  public void testTransferEth() {

    ISendEth eth = new ETH();
    IntentAction intentAction = eth.send("0x009ab4de1234c7066197d6ed75743add3576591f", BigInteger.ONE);

    Intent intent = IntentBuilder.anIntent()
        .withIntentAction(intentAction)
        .withExpiration(BigInteger.TEN.pow(24))
        .withMaxGasLimit(BigInteger.ZERO)
        .withMaxGasPrice(BigInteger.TEN.pow(32))
        .withSalt("0x1111510000000000000000000000000000000000000000000000000000000000")
        .build();

    SignedIntent signedIntent = SignedIntentBuilder.aSignedIntent()
        .withIntent(intent)
        .withWallet(wallet)
        .build();

    Assert.assertEquals(toHexString(signedIntent.getId()),
        "0xf3d5f434a4267ea8cb3fc0b1de85a562933c415610bc33e09f373dab2f1c534f");
  }

  @Test
  public void testIntentWithDependency() {

    IntentAction transferIntentAction = this.erc20
        .transfer(new Address("0x009ab4de1234c7066197d6ed75743add3576591f"),
            new Uint256(BigInteger.ZERO));
    Intent dependencyIntent = IntentBuilder.anIntent()
        .withIntentAction(transferIntentAction)
        .withExpiration(BigInteger.TEN.pow(32))
        .withMaxGasPrice(new BigInteger("9999999999"))
        .withMaxGasLimit(BigInteger.ZERO)
        .build();
    SignedIntent dependencySignedIntent = SignedIntentBuilder.aSignedIntent()
        .withIntent(dependencyIntent)
        .withWallet(wallet)
        .build();


    ISendEth eth = new ETH();
    IntentAction intentAction = eth.send("0x009ab4de1234c7066197d6ed75743add3576591f", BigInteger.valueOf(100 * 10).pow(18));
    Intent intent = IntentBuilder.anIntent()
        .withIntentAction(intentAction)
        .withExpiration(BigInteger.TEN.pow(36))
        .withMaxGasLimit(BigInteger.ZERO)
        .withMaxGasPrice(new BigInteger("9999999999"))
        .withSalt("0x1111510000000000000000000000000000000000000000000000000000000000")
        .withDependencies(Arrays.asList(new IntentDependency(dependencySignedIntent.getId(), wallet.getAddress())))
        .build();

    SignedIntent signedIntent = SignedIntentBuilder.aSignedIntent()
        .withIntent(intent)
        .withWallet(wallet)
        .build();

    Assert.assertEquals(toHexString(signedIntent.getId()),
        "0x60f7d6a24f8936510a3d019c83f59fa5bfcc6372a1bfc4d25ab82f06b073d885");
  }


}
