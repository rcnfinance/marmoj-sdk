package network.marmoj.model.core;

import static org.web3j.crypto.Sign.SignatureData;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SignedIntent{");
        sb.append("intent=").append(intent);
        sb.append(", signature=").append(signature);
        sb.append('}');
        return sb.toString();
    }
}
