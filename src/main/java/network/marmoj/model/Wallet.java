package network.marmoj.model;

import static java.util.Arrays.copyOfRange;
import static network.marmoj.utils.MarmoUtils.sanitizePrefix;
import static network.marmoj.utils.MarmoUtils.toHexStringZeroPadded;
import static org.web3j.crypto.Hash.sha3;
import static org.web3j.utils.Numeric.hexStringToByteArray;
import static org.web3j.utils.Numeric.toHexString;

import network.marmoj.config.Config;
import network.marmoj.exception.ValidationException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

public class Wallet {

  private Config config;
  private Credentials credentials;
  private String signer;
  private String address;

  public Wallet(String key, Config config) {
    this.credentials = Credentials.create(key);
    this.signer = this.credentials.getAddress();
    this.config = config;
    this.address = generateAddress(
        hexStringToByteArray(sanitizePrefix(this.getConfig().getMarmoSork())),
        hexStringToByteArray(sanitizePrefix(this.getConfig().getInitCode())),
        hexStringToByteArray(toHexStringZeroPadded(this.signer))
    );
  }

  public Wallet(String key) {
    this(key, Config.getGlobal());
    if (this.config == null) {
      throw new ValidationException("A valid configuration must be provided or set as global");
    }
  }

  public Config getConfig() {
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
