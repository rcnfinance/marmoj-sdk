package network.marmoj.model.data.domain;

import java.math.BigInteger;
import java.util.List;
import network.marmoj.model.IntentAction;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;

public class Function extends org.web3j.abi.datatypes.Function {

  private String contract;

  public Function(String name, String contract, List<Type> inputParameters,
      List<TypeReference<?>> outputParameters) {
    super(name, inputParameters, outputParameters);
    this.contract = contract;
  }

  public IntentAction encode() {
    return this.encode(BigInteger.ZERO);
  }

  public IntentAction encode(BigInteger value) {
    String data = FunctionEncoder.encode(this);
    IntentAction intentAction = new IntentAction(this.contract, data, value, this);
    return intentAction;
  }

}
