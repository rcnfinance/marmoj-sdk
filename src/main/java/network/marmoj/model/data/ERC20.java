package network.marmoj.model.data;

import network.marmoj.model.IntentAction;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;
import java.util.Arrays;


public class ERC20 implements IERC20 {
    public static final String TRANSFER = "transfer";
    public static final String ALLOWANCE = "allowance";
    public static final String BALANCE_OF = "balanceOf";
    public static final String TOTAL_SUPPLY = "totalSupply";
    public static final String APPROVE = "approve";
    public static final String TRANSFER_FROM = "transferFrom";

    private String contractAddress;

    public ERC20(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    @Override
    public IntentAction totalSupply() {
        final Function function = new Function(TOTAL_SUPPLY,
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint256>() {
                }));
        return getIntentAction(function);
    }

    @Override
    public IntentAction balanceOf(Address who) {
        final Function function = new Function(BALANCE_OF,
                Arrays.asList(who),
                Arrays.asList(new TypeReference<Uint256>() {
                }));
        return getIntentAction(function);
    }

    @Override
    public IntentAction allowance(Address owner, Address spender) {
        final Function function = new Function(ALLOWANCE,
                Arrays.asList(owner, spender),
                Arrays.asList(new TypeReference<Uint256>() {
                }));
        return getIntentAction(function);
    }

    @Override
    public IntentAction transfer(Address to, Uint256 value) {
        final Function function = new Function(TRANSFER,
                Arrays.asList(to, value),
                Arrays.asList(new TypeReference<Bool>() {
                }));
        return getIntentAction(function);
    }

    @Override
    public IntentAction approve(Address spender, Uint256 value) {
        final Function function = new Function(APPROVE,
                Arrays.asList(spender, value),
                Arrays.asList(new TypeReference<Bool>() {
                }));
        return getIntentAction(function);
    }

    @Override
    public IntentAction transferFrom(Address from, Address to, Uint256 value) {
        final Function function = new Function(TRANSFER_FROM,
                Arrays.asList(from, to, value),
                Arrays.asList(new TypeReference<Bool>() {
                }));
        return getIntentAction(function);
    }

    private IntentAction getIntentAction(Function function) {
        return new IntentAction(this.contractAddress, FunctionEncoder.encode(function), BigInteger.ZERO);
    }

}
