package network.marmoj.model.core;

import org.web3j.crypto.Sign;

import static org.web3j.crypto.Sign.*;

public class SignedIntent {

    private Intent intent;
    private SignatureData signature;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public SignatureData getSignature() {
        return signature;
    }

    public void setSignature(SignatureData signature) {
        this.signature = signature;
    }
}
