package network.marmoj.utils;

import network.marmoj.builder.SignedIntentBuilder;
import network.marmoj.model.core.Intent;
import network.marmoj.model.core.SignedIntent;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;

public class MarmoUtils {

    public static SignedIntent sign(Intent intent, Credentials credentials) {
        SignedIntentBuilder signedIntentBuilder = SignedIntentBuilder.aSignedIntent()
                .withIntent(intent)
                .withSignature(Sign.signMessage(intent.getId(), credentials.getEcKeyPair(), false));
        return signedIntentBuilder.build();
    }

    public static String keccak256(String data) {
        return keccak256(Numeric.hexStringToByteArray(data));
    }

    public static String keccak256(byte[] data) {
        Keccak.DigestKeccak kecc = new Keccak.Digest256();
        kecc.update(data, BigDecimal.ZERO.intValue(), data.length);
        return Numeric.toHexString(kecc.digest());
    }

}
