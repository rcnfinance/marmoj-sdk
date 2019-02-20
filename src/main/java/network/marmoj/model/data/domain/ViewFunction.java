package network.marmoj.model.data.domain;

import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;

public class ViewFunction extends Function {

  public ViewFunction(String name, String contract,
      List<Type> inputParameters,
      List<TypeReference<?>> outputParameters) {
    super(name, contract, StateMutability.VIEW, inputParameters, outputParameters);
  }

}
