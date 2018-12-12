package network.marmoj.model.core;

import java.math.BigInteger;
import java.util.Arrays;

public class IntentTx {

    private String to;
    private BigInteger value;
    private byte[] data;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IntentTx{");
        sb.append("to='").append(to).append('\'');
        sb.append(", value=").append(value);
        sb.append(", data=").append(Arrays.toString(data));
        sb.append(", minGasLimit=").append(minGasLimit);
        sb.append(", maxGasPrice=").append(maxGasPrice);
        sb.append('}');
        return sb.toString();
    }
}
