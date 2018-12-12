package network.marmoj.utils;

import network.marmoj.builder.SignedIntentBuilder;
import network.marmoj.model.core.Intent;
import network.marmoj.model.core.SignedIntent;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;

public class MarmoUtils {

    public static SignedIntent sign(Intent intent, Credentials credentials) {
        SignedIntentBuilder signedIntentBuilder = SignedIntentBuilder.aSignedIntent()
                .withIntent(intent)
                .withSignature(Sign.signMessage(intent.getId(), credentials.getEcKeyPair(), false));
        return signedIntentBuilder.build();
    }
}
