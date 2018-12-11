package network.marmoj.builder;

import network.marmoj.model.core.Intent;
import network.marmoj.model.core.SignedIntent;
import org.web3j.crypto.Sign;

import static org.web3j.crypto.Sign.*;

public final class SignedIntentBuilder {
    private Intent intent;
    private SignatureData signature;

    private SignedIntentBuilder() {
    }

    public static SignedIntentBuilder aSignedIntent() {
        return new SignedIntentBuilder();
    }

    public SignedIntentBuilder withIntent(Intent intent) {
        this.intent = intent;
        return this;
    }

    public SignedIntentBuilder withSignature(SignatureData signature) {
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
