package network.marmoj.model.data;

import network.marmoj.model.IntentAction;

import java.math.BigInteger;

public interface ISendEth {

    IntentAction send(String to, BigInteger value);

    IntentAction send(String to, String value);

}
