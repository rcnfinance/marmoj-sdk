package network.marmoj.model.data;

import java.math.BigInteger;
import network.marmoj.model.IntentAction;
import network.marmoj.model.data.domain.StateMutability;
import network.marmoj.model.data.interfaces.ISendEth;

public class ETH implements ISendEth {

  @Override
  public IntentAction send(String to, BigInteger value) {
    return new IntentAction(to, value, StateMutability.PAYABLE);
  }

  @Override
  public IntentAction send(String to, String value) {
    return this.send(to, BigInteger.valueOf(Long.valueOf(value)));
  }

}
