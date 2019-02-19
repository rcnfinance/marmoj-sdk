package network.marmoj.model.data.interfaces;

import java.math.BigInteger;
import network.marmoj.model.IntentAction;

public interface IWEth {
  IntentAction deposit(BigInteger value);
}
