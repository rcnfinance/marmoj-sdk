package network.marmoj.builder;

import static network.marmoj.utils.MarmoUtils.keccak256;

import java.util.Arrays;
import java.util.Collections;
import network.marmoj.model.Dependency;
import network.marmoj.model.IntentDependency;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;

import network.marmoj.Intent;
import network.marmoj.SignedIntent;
import network.marmoj.model.Wallet;
import org.web3j.abi.TypeEncoder;
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
        .append(wallet)
        .append(implementation)
        .append(keccak256(this.buildImplementationCall()));

    return Numeric.hexStringToByteArray(keccak256(encodePackedBuilder.toString()));
  }

  private String buildImplementationCall() {

    StringBuilder builder = new StringBuilder();
    builder.append(TypeEncoder.encode(this.buildDependencyCall()));
    builder.append(TypeEncoder.encode(new Address(this.intent.getTo())));
    builder.append(TypeEncoder.encode(new Uint256(this.intent.getValue())));
    builder.append(TypeEncoder.encode(new Bytes32(this.intent.getData())));
    builder.append(TypeEncoder.encode(new Uint256(this.intent.getMaxGasLimit())));
    builder.append(TypeEncoder.encode(new Uint256(this.intent.getMaxGasPrice())));
    builder.append(TypeEncoder.encode(new Uint256(this.intent.getExpiration())));
    builder.append(TypeEncoder.encode(new Bytes32(this.intent.getSalt())));
    return builder.toString();
  }

  private Bytes32 buildDependencyCall() {
    final int depsCount = this.intent.getDependencies().size();
    if (depsCount == 0) {
      // No dependencies
      return new Bytes32(Numeric.hexStringToByteArray("0x"));
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

      String format = String
          .format("0x%s%s", dependency.getAddress().replace("0x", ""), call
              .replace("0x", ""));
      return new Bytes32(Numeric.hexStringToByteArray(format));
    }

    // Multiple dependencies, using DepsUtils contract
    // TODO: MAKE IMPLEMENTATION
    return null;
  }


}
