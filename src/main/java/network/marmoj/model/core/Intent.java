package network.marmoj.model.core;

import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;

public class  Intent {

    private byte[] id;
    List<byte[]> dependencies;
    private String from;
    private String to;
    private BigInteger value;
    private byte[] data;
    private BigInteger minGasLimit;
    private BigInteger maxGasPrice;
    private byte[] salt;

    public String getId() {
        return Numeric.toHexString(id);
    }

    public byte[] getRawId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public List<byte[]> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<byte[]> dependencies) {
        this.dependencies = dependencies;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
