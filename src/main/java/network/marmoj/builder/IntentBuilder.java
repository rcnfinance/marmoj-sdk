package network.marmoj.builder;

import network.marmoj.exception.ValidationException;
import network.marmoj.model.core.Intent;
import network.marmoj.model.core.IntentAction;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static network.marmoj.utils.MarmoUtils.*;
import static org.web3j.utils.Numeric.toHexStringNoPrefixZeroPadded;

public final class IntentBuilder {
    public static final int SIZE_32 = 32;

    private List<byte[]> dependencies = new ArrayList<>();
    private String signer;
    private String wallet;
    private BigInteger expiration = BigInteger.valueOf(15);
    private byte[] salt = Numeric.toBytesPadded(BigInteger.ZERO, SIZE_32);

    /* For transactions */
    private String to;
    private BigInteger value;
    private byte[] data;
    private BigInteger minGasLimit = BigInteger.valueOf(0);
    private BigInteger maxGasPrice = BigInteger.valueOf(9999999999L);

    private IntentBuilder() { }

    public static IntentBuilder anIntent() {
        IntentBuilder intentBuilder = new IntentBuilder();
        return intentBuilder;
    }

    public IntentBuilder withIntentAction(IntentAction intentAction) {
        this.to = intentAction.getTo();
        this.value = intentAction.getValue();
        this.data = Numeric.hexStringToByteArray(intentAction.getData());
        return this;
    }

    public IntentBuilder withSigner(String signer) {
        this.signer = signer;
        return this;
    }

    public IntentBuilder withWallet(String wallet) {
        this.wallet = wallet;
        return this;
    }

    public IntentBuilder withExpiration(BigInteger days) {
        this.expiration = days;
        return this;
    }

    public IntentBuilder withDependencies(List<byte[]> dependencies) {
        this.dependencies.addAll(dependencies);
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
        if (signer == null) {
            throw new ValidationException("signer");
        }
        if (wallet == null) {
            throw new ValidationException("wallet");
        }
        if (to == null || value == null) {
            throw new ValidationException("intentAction");
        }

        Intent intent = new Intent();
        intent.setSigner(signer);
        intent.setDependencies(dependencies);
        intent.setWallet(wallet);
        intent.setSalt(salt);
        intent.setTo(to);
        intent.setExpiration(expiration);
        intent.setValue(value);
        intent.setData(data);
        intent.setMinGasLimit(minGasLimit);
        intent.setMaxGasPrice(maxGasPrice);
        return intent;
    }



}
