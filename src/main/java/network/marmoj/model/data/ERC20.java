package network.marmoj.model.data;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

public interface ERC20 {

    String totalSupply();
    String balanceOf(Address who);
    String allowance(Address owner, Address spender);
    String transfer(Address to, Uint256 value);
    String approve(Address spender, Uint256 value);
    String transferFrom(Address from, Address to, Uint256 value);

}
