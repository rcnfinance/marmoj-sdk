package network.marmoj.model.data;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.Numeric;

import java.util.Arrays;

public class ERC20Impl implements ERC20 {

    public static final String TRANSFER = "transfer";
    public static final String ALLOWANCE = "allowance";
    public static final String BALANCE_OF = "balanceOf";
    public static final String TOTAL_SUPPLY = "totalSupply";
    public static final String APPROVE = "approve";
    public static final String TRANSFER_FROM = "transferFrom";

    @Override
    public String totalSupply() {
        final Function function = new Function(TOTAL_SUPPLY,
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint256>() {}));
        return FunctionEncoder.encode(function);
    }

    @Override
    public String balanceOf(Address who) {
        final Function function = new Function(BALANCE_OF,
                Arrays.asList(who),
                Arrays.asList(new TypeReference<Uint256>() {}));
        return FunctionEncoder.encode(function);
    }

    @Override
    public String allowance(Address owner, Address spender) {
        final Function function = new Function(ALLOWANCE,
                Arrays.asList(owner, spender),
                Arrays.asList(new TypeReference<Uint256>() {}));
        return FunctionEncoder.encode(function);
    }

    @Override
    public String transfer(Address to, Uint256 value) {
        final Function function = new Function(TRANSFER,
            Arrays.asList(to, value),
            Arrays.asList(new TypeReference<Bool>() {}));
        return FunctionEncoder.encode(function);
    }

    @Override
    public String approve(Address spender, Uint256 value) {
        final Function function = new Function(APPROVE,
                Arrays.asList(spender, value),
                Arrays.asList(new TypeReference<Bool>() {}));
        return FunctionEncoder.encode(function);
    }

    @Override
    public String transferFrom(Address from, Address to, Uint256 value) {
        final Function function = new Function(TRANSFER_FROM,
                Arrays.asList(from, to, value),
                Arrays.asList(new TypeReference<Bool>() {}));
        return FunctionEncoder.encode(function);
    }

}
