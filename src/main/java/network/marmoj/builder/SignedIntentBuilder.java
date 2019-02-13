package network.marmoj.builder;

import static java.text.MessageFormat.format;
import static network.marmoj.utils.MarmoUtils.PREFIX;
import static network.marmoj.utils.MarmoUtils.keccak256;
import static network.marmoj.utils.MarmoUtils.sanitizePrefix;
import static org.web3j.abi.TypeEncoder.encode;
import static org.web3j.utils.Numeric.hexStringToByteArray;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import network.marmoj.Intent;
import network.marmoj.SignedIntent;
import network.marmoj.model.Dependency;
import network.marmoj.model.Wallet;
import network.marmoj.utils.MarmoUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;

public final class SignedIntentBuilder {

  public static final String OUTPUT_FORMAT = "0x{0}{1}";
  private Intent intent;
  private Wallet wallet;

  private SignedIntentBuilder() {
  }

  public static SignedIntentBuilder aSignedIntent() {
    return new SignedIntentBuilder();
  }

  public SignedIntentBuilder withWallet(Wallet wallet) {
    this.wallet = wallet;
    return this;
  }

  public SignedIntentBuilder withIntent(Intent intent) {
    this.intent = intent;
    return this;
  }

  public SignedIntent build() {
    byte[] id = buildId();
    Wallet wallet = this.wallet;
    return new SignedIntent(id, this.intent, wallet.sign(id, wallet.getCredentials()), wallet);
  }

  private byte[] buildId() {
    String wallet = this.wallet.getAddress();
    String implementation = this.wallet.getConfig().getImplementation();

    StringBuilder encodePackedBuilder = new StringBuilder()
        .append(new Address(wallet))
        .append(encode(new Address(implementation)))
        .append(keccak256(this.buildImplementationCall()));

    return hexStringToByteArray(keccak256(encodePackedBuilder.toString()));
  }

  private String buildImplementationCall() {
    StringBuilder encode = new StringBuilder(PREFIX);
    encode.append(encode(this.sanitizeBytes(this.resolveDependencyCall())))
        .append(encode(new Address(this.intent.getTo())))
        .append(encode(new Uint256(this.intent.getValue())))
        .append(encode(this.sanitizeBytes(this.intent.getData())))
        .append(encode(new Uint256(this.intent.getMaxGasPrice())))
        .append(encode(new Uint256(this.intent.getMaxGasLimit())))
        .append(encode(new Uint256(this.intent.getExpiration())))
        .append(encode(sanitizeSalt(this.intent.getSalt())));
    return encode.toString();
  }

  private Bytes32 sanitizeSalt(String salt) {
    if (PREFIX.equals(salt)) {
      return Bytes32.DEFAULT;
    }
    return new Bytes32(hexStringToByteArray(salt));
  }

  private DynamicBytes sanitizeBytes(String bytes) {
    if (PREFIX.equals(bytes)) {
      return DynamicBytes.DEFAULT;
    }
    return new DynamicBytes(hexStringToByteArray(bytes));
  }

  private String resolveDependencyCall() {
    final int depsCount = this.intent.getDependencies().size();
    if (depsCount == 0) { // No dependencies
      return PREFIX;
    }
    if (depsCount == 1) { // Single dependency, call wallet directly
      Dependency dependency = this.intent.getDependencies().iterator().next();
      Function function = new Function(
          "relayedAt",
          Arrays.asList(new Bytes32(dependency.getId())),
          Collections.emptyList()
      );
      String call = FunctionEncoder.encode(function);
      return format("0x{0}{1}", sanitizePrefix(dependency.getAddress()), sanitizePrefix(call));
    } else { // Multiple dependencies, using DepsUtils contract
      Function function = new Function(
          "multipleDeps",
          Arrays.asList(
              new DynamicArray<>(this.intent.getDependencies().stream()
                  .map(dependency -> new Address(dependency.getAddress())).collect(
                      Collectors.toList())),
              new DynamicArray<>(this.intent.getDependencies().stream()
                  .map(dependency -> new Bytes32(dependency.getId())).collect(
                      Collectors.toList()))
          ),
          Collections.emptyList()
      );
      String call = FunctionEncoder.encode(function);
      return format(OUTPUT_FORMAT, sanitizePrefix(this.wallet.getConfig().getDepsUtils()),
          sanitizePrefix(call));
    }
  }

}
