package network.marmoj.builder;

import static network.marmoj.utils.MarmoUtils.PREFIX;
import static network.marmoj.utils.MarmoUtils.keccak256;
import static network.marmoj.utils.MarmoUtils.sanitizePrefix;
import static org.web3j.utils.Numeric.toHexStringNoPrefixZeroPadded;

import java.util.List;
import network.marmoj.Intent;
import network.marmoj.SignedIntent;
import network.marmoj.model.IntentWallet;
import org.web3j.utils.Numeric;

public final class SignedIntentBuilder {

  public static final int SIZE_64 = 64;

  private Intent intent;
  private IntentWallet wallet;

  private SignedIntentBuilder() {
  }

  public static SignedIntentBuilder aSignedIntent() {
    return new SignedIntentBuilder();
  }

  public SignedIntentBuilder withWallet(IntentWallet wallet) {
    this.wallet = wallet;
    return this;
  }

  public SignedIntentBuilder withIntent(Intent intent) {
    this.intent = intent;
    return this;
  }

  public SignedIntent build() {
    byte[] id = buildId();
    IntentWallet wallet = this.wallet;
    return new SignedIntent(id, this.intent, wallet.sign(id, wallet.getCredentials()), wallet);
  }

  private byte[] buildId() {
    String wallet = this.wallet.getAddress();
    String dependencies = keccak256(sanitizeDependencies(this.intent.getDependencies()));
    String to = sanitizePrefix(this.intent.getTo());
    String value = toHexStringNoPrefixZeroPadded(this.intent.getValue(), SIZE_64);
    String data = keccak256(this.intent.getData());
    String minGasLimit = toHexStringNoPrefixZeroPadded(this.intent.getMinGasLimit(), SIZE_64);
    String maxGasLimit = toHexStringNoPrefixZeroPadded(this.intent.getMaxGasPrice(), SIZE_64);
    String salt = sanitizePrefix(Numeric.toHexString(this.intent.getSalt()));
    String expiration = toHexStringNoPrefixZeroPadded(this.intent.getExpiration(), SIZE_64);

    StringBuilder encodePackedBuilder = new StringBuilder()
        .append(wallet)
        .append(dependencies)
        .append(to)
        .append(value)
        .append(data)
        .append(minGasLimit)
        .append(maxGasLimit)
        .append(salt)
        .append(expiration);

    return Numeric.hexStringToByteArray(keccak256(encodePackedBuilder.toString()));
  }

  private String sanitizeDependencies(List<byte[]> dependencies) {
    StringBuilder dependenciesBuiler = new StringBuilder();
    for (byte[] dependency : dependencies) {
      dependenciesBuiler.append(sanitizePrefix(Numeric.toHexString(dependency)));
    }
    String result = dependenciesBuiler.toString();
    if (!result.isEmpty()) {
      return PREFIX + result;
    }
    return result;
  }
}
