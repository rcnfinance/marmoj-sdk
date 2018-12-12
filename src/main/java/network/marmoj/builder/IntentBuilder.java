package network.marmoj.builder;

import jdk.internal.jline.internal.Nullable;
import network.marmoj.model.core.Intent;
import network.marmoj.model.core.IntentAction;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public final class IntentBuilder {

    public static final int LENGTH = 32;

    private List<byte[]> dependencies = new ArrayList<>();
    private byte[] id;
    private String signer;
    private String wallet;
    @Nullable
    private byte[] salt = Numeric.toBytesPadded(BigInteger.ZERO, LENGTH);
    private IntentTxBuilder intentTxBuilder = IntentTxBuilder.anIntentTx();

    private IntentBuilder() { }

    public static IntentBuilder anIntent() {
        IntentBuilder intentBuilder = new IntentBuilder();
        intentBuilder.id = "123".getBytes();
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

        Intent intent = new Intent();
        intent.setId(id);
        intent.setSigner(signer);
        intent.setDependencies(dependencies);
        intent.setWallet(wallet);
        intent.setTx(intentTxBuilder.build());
        intent.setSalt(salt);
        return intent;
    }

}
