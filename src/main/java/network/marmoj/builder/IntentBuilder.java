package network.marmoj.builder;

import network.marmoj.model.core.Intent;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;

public final class IntentBuilder {
    List<byte[]> dependencies;
    private String id;
    private String from;
    private String to;
    private BigInteger value;
    private byte[] data;
    private BigInteger minGasLimit;
    private BigInteger maxGasPrice;
    private byte[] salt;

    private IntentBuilder() {
    }

    public static IntentBuilder anIntent() {
        return new IntentBuilder();
    }

    public IntentBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public IntentBuilder withDependencies(List<byte[]> dependencies) {
        this.dependencies = dependencies;
        return this;
    }

    public IntentBuilder withFrom(String from) {
        this.from = from;
        return this;
    }

    public IntentBuilder withTo(String to) {
        this.to = to;
        return this;
    }

    public IntentBuilder withValue(BigInteger value) {
        this.value = value;
        return this;
    }

    public IntentBuilder withData(byte[] data) {
        this.data = data;
        return this;
    }

    public IntentBuilder withMinGasLimit(BigInteger minGasLimit) {
        this.minGasLimit = minGasLimit;
        return this;
    }

    public IntentBuilder withMaxGasPrice(BigInteger maxGasPrice) {
        this.maxGasPrice = maxGasPrice;
        return this;
    }

    public IntentBuilder withSalt(byte[] salt) {
        this.salt = salt;
        return this;
    }

    public Intent build() {
        Intent intent = new Intent();
        intent.setId(id);
        intent.setDependencies(dependencies);
        intent.setFrom(from);
        intent.setTo(to);
        intent.setValue(value);
        intent.setData(data);
        intent.setMinGasLimit(minGasLimit);
        intent.setMaxGasPrice(maxGasPrice);
        intent.setSalt(salt);
        return intent;
    }
}
