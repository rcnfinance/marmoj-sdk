package network.marmoj.model.data;

import java.math.BigInteger;
import network.marmoj.model.IntentAction;

public class ETH implements ISendEth {

  @Override
  public IntentAction send(String to, BigInteger value) {
    return new IntentAction(to, "0x0", value);
  }

  @Override
  public IntentAction send(String to, String value) {
    return this.send(to, BigInteger.valueOf(Long.valueOf(value)));
  }

}
