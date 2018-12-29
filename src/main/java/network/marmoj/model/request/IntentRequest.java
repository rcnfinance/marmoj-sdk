package network.marmoj.model.request;

import java.util.List;

public class IntentRequest {
    private String id;
    List<String> dependencies;
    private String wallet;
    private IntentTxRequest tx;
    private String salt;
    private String signer;
    private String expiration;
    private SignatureDataRequest signature;

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

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
