package network.marmoj.builder;

import static network.marmoj.utils.MarmoUtils.*;
import static network.marmoj.utils.MarmoUtils.keccak256;
import static network.marmoj.utils.MarmoUtils.sanitizePrefix;
import static org.web3j.abi.TypeEncoder.*;
import static org.web3j.utils.Numeric.hexStringToByteArray;

import java.util.Arrays;
import java.util.Collections;
import network.marmoj.Intent;
import network.marmoj.SignedIntent;
import network.marmoj.model.Dependency;
import network.marmoj.model.Wallet;
import network.marmoj.utils.MarmoUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.Numeric;

public final class SignedIntentBuilder {

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
    encode.append(encode(new DynamicBytes(hexStringToByteArray(this.buildDependencyCall()))));
    encode.append(encode(new Address(this.intent.getTo())));
    encode.append(encode(new Uint256(this.intent.getValue())));
    encode.append(encode(new DynamicBytes(hexStringToByteArray(this.intent.getData()))));
    encode.append(encode(new Uint256(this.intent.getMaxGasPrice())));
    encode.append(encode(new Uint256(this.intent.getMaxGasLimit())));
    encode.append(encode(new Uint256(this.intent.getExpiration())));
    encode.append(encode(new DynamicBytes(this.intent.getSalt())));
    System.out.print(encode.toString());
    return encode.toString();
  }

  private String buildDependencyCall() {
    final int depsCount = this.intent.getDependencies().size();
    if (depsCount == 0) {
      // No dependencies
      return "0x0";
    }

    if (depsCount == 1) {
      // Single dependency, call wallet directly
      Dependency dependency = this.intent.getDependencies().iterator().next();
      Function function = new Function(
          "relayedAt",
          Arrays.asList(new Bytes32(dependency.getId())),
          Collections.emptyList()
      );
      String call = FunctionEncoder.encode(function);
      return String.format("0x%s%s", sanitizePrefix(dependency.getAddress()), sanitizePrefix(call));
    } else {
      // Multiple dependencies, using DepsUtils contract
      // TODO: MAKE IMPLEMENTATION
      return null;
    }
  }


}
