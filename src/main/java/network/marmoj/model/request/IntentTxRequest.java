package network.marmoj.model.request;

import java.math.BigInteger;

public class IntentTxRequest {
    private String to;
    private BigInteger value;
    private String data;
    private BigInteger minGasLimit;
    private BigInteger maxGasPrice;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BigInteger getMinGasLimit() {
        return minGasLimit;
    }

    public void setMinGasLimit(BigInteger minGasLimit) {
        this.minGasLimit = minGasLimit;
    }

    public BigInteger getMaxGasPrice() {
        return maxGasPrice;
    }

    public void setMaxGasPrice(BigInteger maxGasPrice) {
        this.maxGasPrice = maxGasPrice;
    }
}
