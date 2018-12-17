package network.marmoj.model.core;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Intent {

    private byte[] id;
    List<byte[]> dependencies;
    private String wallet;
    private byte[] salt;
    private String signer;
    /* For Transactions */
    private String to;
    private BigInteger value;
    private byte[] data;
    private BigInteger minGasLimit;
    private BigInteger maxGasPrice;

    public byte[] getId() {
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

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Intent{");
        sb.append("id=").append(Arrays.toString(id));
        sb.append(", dependencies=").append(dependencies);
        sb.append(", wallet='").append(wallet).append('\'');
        sb.append(", salt=").append(Arrays.toString(salt));
        sb.append(", signer='").append(signer).append('\'');
        sb.append(", to='").append(to).append('\'');
        sb.append(", value=").append(value);
        sb.append(", data=").append(Arrays.toString(data));
        sb.append(", minGasLimit=").append(minGasLimit);
        sb.append(", maxGasPrice=").append(maxGasPrice);
        sb.append('}');
        return sb.toString();
    }
}
