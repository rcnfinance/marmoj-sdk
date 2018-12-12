package network.marmoj.builder;

import network.marmoj.model.core.Intent;
import network.marmoj.model.core.IntentTx;

import java.util.List;

public final class IntentBuilder {
    List<byte[]> dependencies;
    private byte[] id;
    private String signer;
    private String wallet;
    private IntentTx intentTx;
    private byte[] salt;

    private IntentBuilder() {
    }

    public static IntentBuilder anIntent() {
        return new IntentBuilder();
    }

    public IntentBuilder withId(byte[] id) {
        this.id = id;
        return this;
    }

    public IntentBuilder withSigner(String signer) {
        this.signer = signer;
        return this;
    }

    public IntentBuilder withDependencies(List<byte[]> dependencies) {
        this.dependencies = dependencies;
        return this;
    }

    public IntentBuilder withWallet(String wallet) {
        this.wallet = wallet;
        return this;
    }

    public IntentBuilder withIntentTx(IntentTx intentTx) {
        this.intentTx = intentTx;
        return this;
    }

    public IntentBuilder withSalt(byte[] salt) {
        this.salt = salt;
        return this;
    }

    public Intent build() {
        Intent intent = new Intent();
        intent.setId(id);
        intent.setSigner(signer);
        intent.setDependencies(dependencies);
        intent.setWallet(wallet);
        intent.setTx(intentTx);
        intent.setSalt(salt);
        return intent;
    }

}
