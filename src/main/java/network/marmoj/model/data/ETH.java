package network.marmoj.model.data;

import network.marmoj.model.IntentAction;

import java.math.BigInteger;

public class ETH implements ISendEth {

    @Override
    public IntentAction send(String to, BigInteger value) {
        return new IntentAction(to, "0x", value);
    }

    @Override
    public IntentAction send(String to, String value) {
        return send(to, BigInteger.valueOf(Long.valueOf(value)));
    }

}
