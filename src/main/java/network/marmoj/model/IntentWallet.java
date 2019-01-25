package network.marmoj.model;

import network.marmoj.config.Config;
import network.marmoj.utils.MarmoUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Arrays;

import static java.util.Arrays.copyOfRange;
import static network.marmoj.utils.MarmoUtils.*;
import static org.web3j.crypto.Hash.sha3;
import static org.web3j.utils.Numeric.*;

public class IntentWallet {

    private Config config;
    private Credentials credentials;
    private String signer;
    private String address;

    public IntentWallet(String key, Config config) {
        this.credentials = Credentials.create(key);
        this.signer = this.credentials.getAddress();
        this.config = config;
        this.address = generateAddress(
                hexStringToByteArray(sanitizePrefix(this.getConfig().getMarmoSork())),
                hexStringToByteArray(sanitizePrefix(this.getConfig().getInitCode())),
                hexStringToByteArray(toHexStringZeroPadded(this.signer))
        );
        System.out.println(this.address);
        System.out.println(this.signer);
        System.out.println(this.config.getMarmoSork());
        System.out.println(this.config.getInitCode());
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

    private String generateAddress(byte[] senderAddr, byte[] initCode, byte[] salt) {
        // 1 - 0xff length, 32 bytes - keccak-256
        byte[] data = new byte[1 + senderAddr.length + salt.length + 32];
        data[0] = (byte) 0xff;
        int currentOffset = 1;
        System.arraycopy(senderAddr, 0, data, currentOffset, senderAddr.length);
        currentOffset += senderAddr.length;
        System.arraycopy(salt, 0, data, currentOffset, salt.length);
        currentOffset += salt.length;
        System.arraycopy(initCode, 0, data, currentOffset, initCode.length);

        byte[] hash = sha3(data);
        return toHexString(copyOfRange(hash, 12, hash.length));
    }
}
