package network.marmoj.builder;

import network.marmoj.model.core.IntentTx;

import java.math.BigInteger;

public final class IntentTxBuilder {
    private String to;
    private BigInteger value;
    private byte[] data;
    private BigInteger minGasLimit = BigInteger.valueOf(0);
    private BigInteger maxGasPrice = BigInteger.valueOf(9999999999L);

    private IntentTxBuilder() { }

    public static IntentTxBuilder anIntentTx() {
        return new IntentTxBuilder();
    }

    public IntentTxBuilder withTo(String to) {
        this.to = to;
        return this;
    }

    public IntentTxBuilder withValue(BigInteger value) {
        this.value = value;
        return this;
    }

    public IntentTxBuilder withData(byte[] data) {
        this.data = data;
        return this;
    }

    public IntentTxBuilder withMinGasLimit(BigInteger minGasLimit) {
        this.minGasLimit = minGasLimit;
        return this;
    }

    public IntentTxBuilder withMaxGasPrice(BigInteger maxGasPrice) {
        this.maxGasPrice = maxGasPrice;
        return this;
    }

    public IntentTx build() {


        //FIXME VALIDACIONES DE LOS OPCIONES Y NO OPCIONES

        IntentTx intentTx = new IntentTx();
        intentTx.setTo(to);
        intentTx.setValue(value);
        intentTx.setData(data);
        intentTx.setMinGasLimit(minGasLimit);
        intentTx.setMaxGasPrice(maxGasPrice);
        return intentTx;
    }
}
