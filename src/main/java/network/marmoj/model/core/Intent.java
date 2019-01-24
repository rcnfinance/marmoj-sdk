package network.marmoj.model.core;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Intent {
    List<byte[]> dependencies;
    private byte[] salt;
    private BigInteger expiration;
    /* For Transactions */
    private String to;
    private BigInteger value;
    private byte[] data;
    private BigInteger minGasLimit;
    private BigInteger maxGasPrice;

    public Intent(List<byte[]> dependencies, byte[] salt, String to, BigInteger expiration, BigInteger value,
                  byte[] data, BigInteger minGasLimit, BigInteger maxGasPrice) {
        this.dependencies = dependencies;
        this.salt = salt;
        this.expiration = expiration;
        this.to = to;
        this.value = value;
        this.data = data;
        this.minGasLimit = minGasLimit;
        this.maxGasPrice = maxGasPrice;
    }

    public List<byte[]> getDependencies() {
        return dependencies;
    }

    public BigInteger getExpiration() {
        return expiration;
    }

    public byte[] getSalt() {
        return salt;
    }

    public String getTo() {
        return to;
    }

    public BigInteger getValue() {
        return value;
    }

    public byte[] getData() {
        return data;
    }

    public BigInteger getMinGasLimit() {
        return minGasLimit;
    }

    public BigInteger getMaxGasPrice() {
        return maxGasPrice;
    }

}
