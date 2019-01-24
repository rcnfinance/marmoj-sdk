package network.marmoj.model.core;

import network.marmoj.config.Config;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

public class IntentWallet {

    private Config config;
    private Credentials credentials;
    private String signer;
    private String address;

    public IntentWallet(String key, Config config) {
        this.credentials = Credentials.create(key);
        this.config = config;
    }

    public Config getConfig() {
        if (config == null) {
            config = Config.getInstance();
        }
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SignatureData sign(byte[] id, Credentials credentials) {
        return Sign.signMessage(id, credentials.getEcKeyPair(), false);
    }


    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
