package network.marmoj.builder;

import network.marmoj.model.core.Intent;
import network.marmoj.model.core.SignedIntent;

public final class SignedIntentBuilder {
    private Intent intent;
    private String signature;

    private SignedIntentBuilder() {
    }

    public static SignedIntentBuilder aSignedIntent() {
        return new SignedIntentBuilder();
    }

    public SignedIntentBuilder withIntent(Intent intent) {
        this.intent = intent;
        return this;
    }

    public SignedIntentBuilder withSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public SignedIntent build() {
        SignedIntent signedIntent = new SignedIntent();
        signedIntent.setIntent(intent);
        signedIntent.setSignature(signature);
        return signedIntent;
    }
}
