package network.marmoj.model.data;

import java.util.Arrays;
import network.marmoj.model.IntentAction;
import network.marmoj.model.data.domain.Function;
import network.marmoj.model.data.domain.ViewFunction;
import network.marmoj.model.data.interfaces.IERC20;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.generated.Uint256;


public class ERC20 extends Contract implements IERC20 {

  public static final String TRANSFER = "transfer";
  public static final String ALLOWANCE = "allowance";
  public static final String BALANCE_OF = "balanceOf";
  public static final String TOTAL_SUPPLY = "totalSupply";
  public static final String APPROVE = "approve";
  public static final String TRANSFER_FROM = "transferFrom";


  public ERC20(String contractAddress) {
    super(contractAddress);
  }

  @Override
  public IntentAction totalSupply() {
    final Function function = new ViewFunction(
        TOTAL_SUPPLY,
        contractAddress,
        Arrays.asList(),
        Arrays.asList(new TypeReference<Uint256>() {
        }));
    return function.encode();
  }

  @Override
  public IntentAction balanceOf(Address who) {
    final Function function = new ViewFunction(
        BALANCE_OF,
        contractAddress,
        Arrays.asList(who),
        Arrays.asList(new TypeReference<Uint256>() {
        }));
    return function.encode();
  }

  @Override
  public IntentAction allowance(Address owner, Address spender) {
    final Function function = new ViewFunction(
        ALLOWANCE,
        contractAddress,
        Arrays.asList(owner, spender),
        Arrays.asList(new TypeReference<Uint256>() {
        }));
    return function.encode();
  }

  @Override
  public IntentAction transfer(Address to, Uint256 value) {
    final Function function = new Function(
        TRANSFER,
        contractAddress,
        Arrays.asList(to, value),
        Arrays.asList(new TypeReference<Bool>() {
        }));
    return function.encode();
  }

  @Override
  public IntentAction approve(Address spender, Uint256 value) {
    final Function function = new Function(
        APPROVE,
        contractAddress,
        Arrays.asList(spender, value),
        Arrays.asList(new TypeReference<Bool>() {
        }));
    return function.encode();
  }

  @Override
  public IntentAction transferFrom(Address from, Address to, Uint256 value) {
    final Function function = new Function(
        TRANSFER_FROM,
        contractAddress,
        Arrays.asList(from, to, value),
        Arrays.asList(new TypeReference<Bool>() {
        }));
    return function.encode();
  }

}
