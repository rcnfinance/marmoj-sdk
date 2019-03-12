package network.marmoj.model.data.interfaces;

import java.math.BigInteger;
import network.marmoj.model.IntentAction;

public interface ISendEth {

  IntentAction send(String to, BigInteger value);

  IntentAction send(String to, String value);

}
