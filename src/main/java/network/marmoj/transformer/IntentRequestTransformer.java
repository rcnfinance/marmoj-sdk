package network.marmoj.transformer;

import network.marmoj.model.core.Intent;
import network.marmoj.model.core.SignedIntent;
import network.marmoj.model.request.IntentRequest;
import org.web3j.utils.Numeric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.web3j.crypto.Sign.SignatureData;

public class IntentRequestTransformer {

    final static Function<SignedIntent, IntentRequest> function = signedIntent -> {
        IntentRequest request = new IntentRequest();
        Intent intent = signedIntent.getIntent();
        request.setId(Numeric.toHexString(intent.getId()));
        List<String> dependencies = intent.getDependencies()
                .stream()
                .map(it -> Numeric.toHexString(it))
                .collect(Collectors.toList());
        request.setDependencies(dependencies);
        request.setSalt(Numeric.toHexString(intent.getSalt()));
        request.setWallet(intent.getWallet());

        SignatureData signature = signedIntent.getSignature();

        String r = Numeric.toHexString(signature.getR());
        String s = Numeric.toHexString(signature.getS());
        String v = String.valueOf(Integer.toString(signature.getV()));

        request.setSignature(String.format("%s%s%s", r, s, v));
        request.setId(Numeric.toHexString(intent.getId()));
        request.setTx(IntentTxRequestTransformer.transform(intent.getTx()));
        return request;
    };

    public static IntentRequest transform(SignedIntent signedIntent) {
        return function.apply(signedIntent);
    }


}
