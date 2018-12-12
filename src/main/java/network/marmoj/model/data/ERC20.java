package network.marmoj.model.data;

import network.marmoj.model.core.IntentAction;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

public interface ERC20 {

    IntentAction totalSupply();
    IntentAction balanceOf(Address who);
    IntentAction allowance(Address owner, Address spender);
    IntentAction transfer(Address to, Uint256 value);
    IntentAction approve(Address spender, Uint256 value);
    IntentAction transferFrom(Address from, Address to, Uint256 value);

}
