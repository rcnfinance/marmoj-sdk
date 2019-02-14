package network.marmoj.model.data;

import java.math.BigInteger;
import java.util.Arrays;
import network.marmoj.model.IntentAction;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;

public class WEth extends ERC20 implements IWEth {

  public WEth(String contractAddress) {
    super(contractAddress);
  }

  public IntentAction deposit(BigInteger value) {
    final Function function = new Function(TOTAL_SUPPLY,
        Arrays.asList(),
        Arrays.asList(new TypeReference<Uint256>() {
        }));
    return this.getIntentAction(function, value);
  }
}
