package network.marmoj.builder;

import network.marmoj.model.core.Intent;
import network.marmoj.model.core.IntentAction;
import network.marmoj.model.data.ERC20;
import org.junit.Assert;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.utils.Numeric;

public class IntentBuilderTest {

    /*
        Test with:
        - ERC20 Transfer
        - 1 Token
        - Signer
        - Wallet (0xDc3914BEd4Fc2E387d0388B2E3868e671c143944)
        - IntentAction (0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB, 1)
     */
    @Test
    public void IntentBuilderOne() {
        String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4"; //RCN Token
        String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";

        ERC20 erc20 = new ERC20(tokenContractAddress);
        int value = 1;
        IntentAction intentAction = erc20.transfer(
                new Address(to),
                new Uint256(value)
        );

        Credentials credentials = Credentials.create("512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");
        String contractAddress = "0xDc3914BEd4Fc2E387d0388B2E3868e671c143944";
        Intent intent = IntentBuilder.anIntent()
                .withSigner(credentials.getAddress())
                .withWallet(contractAddress)
                .withIntentAction(intentAction)
                .build();

        Assert.assertEquals(Numeric.toHexString(intent.getId()), "0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928");
    }

    /*
        Test with:
        - balanceOf
        - Signer
        - Wallet (0x692a70d2e424a56d2c6c27aa97d1a86395877b3a)
        - IntentAction (0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB, 1)
     */
    @Test
    public void IntentBuilderTwo() {
        String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4"; //RCN TOKEN
        String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";

        ERC20 erc20 = new ERC20(tokenContractAddress);
        IntentAction intentAction = erc20.balanceOf(
                new Address(to)
        );

        Credentials credentials = Credentials.create("512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");
        String contractAddress = "0xbbf289d846208c16edc8474705c748aff07732db";
        Intent intent = IntentBuilder.anIntent()
                .withSigner(credentials.getAddress())
                .withWallet(contractAddress)
                .withIntentAction(intentAction)
                .build();

        Assert.assertEquals(Numeric.toHexString(intent.getId()), "0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e");
    }


}
