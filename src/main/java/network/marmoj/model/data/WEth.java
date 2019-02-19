package network.marmoj.model.data;

import java.math.BigInteger;
import java.util.Arrays;
import network.marmoj.model.IntentAction;
import network.marmoj.model.data.domain.Function;
import network.marmoj.model.data.interfaces.IWEth;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.generated.Uint256;

public class WEth extends ERC20 implements IWEth {

  public WEth(String contractAddress) {
    super(contractAddress);
  }

  public IntentAction deposit(BigInteger value) {
    final Function function = new Function(
        TOTAL_SUPPLY,
        contractAddress,
        Arrays.asList(),
        Arrays.asList(new TypeReference<Uint256>() {
        }));
    return function.encode(value);
  }
}
