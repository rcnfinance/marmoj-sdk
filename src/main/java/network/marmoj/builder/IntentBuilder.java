package network.marmoj.builder;

import network.marmoj.model.core.Intent;
import network.marmoj.model.core.IntentAction;
import network.marmoj.model.core.IntentTx;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.bouncycastle.jcajce.provider.digest.Keccak.*;

public final class IntentBuilder {

    public static final int SIZE_32 = 32;
    public static final int SIZE_64 = 64;
    public static final int SIZE_PREFIX = 2;
    public static final String PREFIX = "0x";

    private List<byte[]> dependencies = new ArrayList<>();
    private String signer;
    private String wallet;
    private byte[] salt = Numeric.toBytesPadded(BigInteger.ZERO, SIZE_32);
    private IntentTxBuilder intentTxBuilder = IntentTxBuilder.anIntentTx();

    private IntentBuilder() { }

    public static IntentBuilder anIntent() {
        IntentBuilder intentBuilder = new IntentBuilder();
        return intentBuilder;
    }

    public IntentBuilder withIntentAction(IntentAction intentAction) {
        this.intentTxBuilder.withTo(intentAction.getTo());
        this.intentTxBuilder.withValue(intentAction.getValue());
        this.intentTxBuilder.withData(Numeric.hexStringToByteArray(intentAction.getData()));
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

    public IntentBuilder withDependencies(List<byte[]> dependencies) {
        this.dependencies.addAll(dependencies);
        return this;
    }

    public IntentBuilder withMinGasLimit(BigInteger minGasLimit) {
        this.intentTxBuilder.withMinGasLimit(minGasLimit);
        return this;
    }

    public IntentBuilder withMaxGasPrice(BigInteger maxGasPrice) {
        this.intentTxBuilder.withMaxGasPrice(maxGasPrice);
        return this;
    }

    public IntentBuilder withSalt(byte[] salt) {
        this.salt = salt;
        return this;
    }

    public Intent build() {

        //FIXME VALIDACIONES DE LOS OPCIONES Y NO OPCIONES
        IntentTx intentTx = intentTxBuilder.build();

        Intent intent = new Intent();
        intent.setId(generateId(intentTx));
        intent.setSigner(signer);
        intent.setDependencies(dependencies);
        intent.setWallet(wallet);
        intent.setTx(intentTx);
        intent.setSalt(salt);
        return intent;
    }

    private byte[] generateId(IntentTx intentTx) {

        String wallet = this.wallet;
        String dependencies = sanitizePrefix(keccak256(sanitizeDependencies(this.dependencies)));
        String to = sanitizePrefix(intentTx.getTo());
        String value = Numeric.toHexStringNoPrefixZeroPadded(intentTx.getValue(), SIZE_64);
        String data = sanitizePrefix(keccak256(intentTx.getData()));
        String minGasLimit = Numeric.toHexStringNoPrefixZeroPadded(intentTx.getMinGasLimit(), SIZE_64);
        String maxGasLimit = Numeric.toHexStringNoPrefixZeroPadded(intentTx.getMaxGasPrice(), SIZE_64);
        String salt = sanitizePrefix(Numeric.toHexString(this.salt));

        StringBuilder encodePackedBuilder = new StringBuilder()
                .append(wallet)
                .append(dependencies)
                .append(to)
                .append(value)
                .append(data)
                .append(minGasLimit)
                .append(maxGasLimit)
                .append(salt);

        String encodePacked = keccak256(encodePackedBuilder.toString());
        return Numeric.hexStringToByteArray(encodePacked);
    }

    private String sanitizePrefix(String data) {
        return data.substring(SIZE_PREFIX);
    }

    private String sanitizeDependencies(List<byte[]> dependencies) {
        StringBuilder dependenciesBuiler = new StringBuilder();
        for (byte[] dependency: dependencies) {
            dependenciesBuiler.append(sanitizePrefix(Numeric.toHexString(dependency)));
        }
        String result = dependenciesBuiler.toString();
        if (!result.isEmpty()) {
            return PREFIX + result;
        }
        return result;
    }

    private String keccak256(String data) {
        return keccak256(Numeric.hexStringToByteArray(data));
    }

    private String keccak256(byte[] data) {
        DigestKeccak kecc = new Digest256();
        kecc.update(data, BigDecimal.ZERO.intValue(), data.length);
        return Numeric.toHexString(kecc.digest());
    }

}
