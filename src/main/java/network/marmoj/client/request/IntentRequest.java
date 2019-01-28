package network.marmoj.client.request;

import network.marmoj.Intent;
import network.marmoj.SignedIntent;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class IntentRequest {
    private String id;
    List<String> dependencies;
    private String wallet;
    private IntentTxRequest tx;
    private String salt;
    private String signer;
    private BigInteger expiration;
    private SignatureDataRequest signature;

    public IntentRequest(SignedIntent signedIntent) {
        Intent intent = signedIntent.getIntent();
        List<String> dependencies = intent.getDependencies()
                .stream()
                .map(it -> Numeric.toHexString(it))
                .collect(Collectors.toList());
        this.dependencies = dependencies;
        this.salt = Numeric.toHexString(intent.getSalt());
        this.expiration = intent.getExpiration();
        this.wallet = signedIntent.getWallet().getAddress();
        this.signer = signedIntent.getWallet().getSigner();
        this.signature = new SignatureDataRequest(signedIntent.getSignature());
        this.id = Numeric.toHexString(signedIntent.getId());
        this.tx = new IntentTxRequest(intent);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public IntentTxRequest getTx() {
        return tx;
    }

    public void setTx(IntentTxRequest tx) {
        this.tx = tx;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public SignatureDataRequest getSignature() {
        return signature;
    }

    public void setSignature(SignatureDataRequest signature) {
        this.signature = signature;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public BigInteger getExpiration() {
        return expiration;
    }

    public void setExpiration(BigInteger expiration) {
        this.expiration = expiration;
    }

}

