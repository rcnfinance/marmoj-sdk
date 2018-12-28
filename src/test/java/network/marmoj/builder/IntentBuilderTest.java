package network.marmoj.builder;

import network.marmoj.exception.ValidationException;
import network.marmoj.model.core.Intent;
import network.marmoj.model.core.IntentAction;
import network.marmoj.model.data.ERC20;
import org.junit.Assert;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.web3j.utils.Numeric.hexStringToByteArray;
import static org.web3j.utils.Numeric.toHexString;

//TODO: Add assert to other attributes.
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
    public void testGenerateIdSuccessOne() {
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

        Assert.assertEquals(toHexString(intent.getId()), "0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928");
    }

    /*
        Test with:
        - balanceOf
        - Signer
        - Wallet (0x692a70d2e424a56d2c6c27aa97d1a86395877b3a)
        - IntentAction (0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB, 1)
     */
    @Test
    public void testGenerateIdSuccessTwo() {
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

        Assert.assertEquals(toHexString(intent.getId()), "0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e");
    }

    /*
        Test with:
        - balanceOf
        - Signer
        - dependencies (0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e)
        - Wallet (0x692a70d2e424a56d2c6c27aa97d1a86395877b3a)
        - IntentAction (0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB, 1)
     */
    @Test
    public void testGenerateIdSuccessOneDependency() {
        String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4"; //RCN TOKEN
        String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";

        ERC20 erc20 = new ERC20(tokenContractAddress);
        IntentAction intentAction = erc20.balanceOf(
                new Address(to)
        );

        Credentials credentials = Credentials.create("512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");
        String contractAddress = "0xbbf289d846208c16edc8474705c748aff07732db";
        Intent intent = IntentBuilder.anIntent()
                .withDependencies(Arrays.asList(hexStringToByteArray("0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e")))
                .withSigner(credentials.getAddress())
                .withWallet(contractAddress)
                .withIntentAction(intentAction)
                .build();

        Assert.assertEquals(toHexString(intent.getId()), "0x19ca8e36872eaf21cd75c9319cfd08769b61fcb7c8ab119d71960c27585595af");
    }

    /*
        Test with:
        - balanceOf
        - Signer
        - dependencies (0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e, 0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928)
        - Wallet (0x692a70d2e424a56d2c6c27aa97d1a86395877b3a)
        - IntentAction (0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB, 1)
     */
    @Test
    public void testGenerateIdSuccessTwoDependency() {
        String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4"; //RCN TOKEN
        String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";

        ERC20 erc20 = new ERC20(tokenContractAddress);
        IntentAction intentAction = erc20.balanceOf(
                new Address(to)
        );

        Credentials credentials = Credentials.create("512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");
        String contractAddress = "0xbbf289d846208c16edc8474705c748aff07732db";
        List<byte[]> dependencies = Arrays.asList(
                hexStringToByteArray("0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e"),
                hexStringToByteArray("0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928")
        );
        Intent intent = IntentBuilder.anIntent()
                .withDependencies(dependencies)
                .withSigner(credentials.getAddress())
                .withWallet(contractAddress)
                .withIntentAction(intentAction)
                .build();

        Assert.assertEquals(toHexString(intent.getId()), "0xab4b18a2b163ac552a6d2eac23529e4d5e25ff54c41831b75e8c169a03f39a20");
    }

    /*
        Test with:
        - balanceOf
        - Signer
        - dependencies (0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e, 0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928)
        - Wallet (0x692a70d2e424a56d2c6c27aa97d1a86395877b3a)
        - Min gas 300000
        - Max gas 999999
        - IntentAction (0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB, 1)
     */
    @Test
    public void testGenerateIdSuccessWithOptionalParams() {
        String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4"; //RCN TOKEN
        String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";

        ERC20 erc20 = new ERC20(tokenContractAddress);
        IntentAction intentAction = erc20.balanceOf(
                new Address(to)
        );

        Credentials credentials = Credentials.create("512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");
        String contractAddress = "0xbbf289d846208c16edc8474705c748aff07732db";
        List<byte[]> dependencies = Arrays.asList(
                hexStringToByteArray("0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e"),
                hexStringToByteArray("0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928")
        );
        Intent intent = IntentBuilder.anIntent()
                .withDependencies(dependencies)
                .withSigner(credentials.getAddress())
                .withWallet(contractAddress)
                .withMinGasLimit(BigInteger.valueOf(300000))
                .withMaxGasPrice(BigInteger.valueOf(999999))
                .withIntentAction(intentAction)
                .build();

        Assert.assertEquals(toHexString(intent.getId()), "0x9ef832fe6023c21990339fe87724fe5a19fdb4697ce32769c238eb6ab9b92b2c");
    }

    /*
        Test with:
        - balanceOf
        - Signer
        - dependencies (0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e, 0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928)
        - Wallet (0x692a70d2e424a56d2c6c27aa97d1a86395877b3a)
        - Min gas 300000
        - Max gas 999999
        - IntentAction (0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB, 1)
        - Salt (0x0000000000000000000000000000000000000000000000000000000000000001)
     */
    @Test
    public void testGenerateIdSuccessWithSaltOne() {
        String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4"; //RCN TOKEN
        String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";

        ERC20 erc20 = new ERC20(tokenContractAddress);
        IntentAction intentAction = erc20.balanceOf(
                new Address(to)
        );

        Credentials credentials = Credentials.create("512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");
        String contractAddress = "0xbbf289d846208c16edc8474705c748aff07732db";
        List<byte[]> dependencies = Arrays.asList(
                hexStringToByteArray("0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e"),
                hexStringToByteArray("0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928")
        );
        Intent intent = IntentBuilder.anIntent()
                .withDependencies(dependencies)
                .withSigner(credentials.getAddress())
                .withWallet(contractAddress)
                .withMinGasLimit(BigInteger.valueOf(300000))
                .withMaxGasPrice(BigInteger.valueOf(999999))
                .withIntentAction(intentAction)
                .withSalt(hexStringToByteArray("0x0000000000000000000000000000000000000000000000000000000000000001"))
                .build();

        Assert.assertEquals(toHexString(intent.getId()), "0xfc1e9fd25abd26a1be78817f0675a5051285af23957ca0322f2925d93f291ec5");
    }

    /*
        Test with:
        - balanceOf
        - Signer
        - dependencies (0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e, 0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928)
        - Wallet (0x692a70d2e424a56d2c6c27aa97d1a86395877b3a)
        - Min gas 300000
        - Max gas 999999
        - IntentAction (0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB, 1)
        - Salt (0x0000000000000000000000000000000000000000000000000000000000000001)
     */
    @Test
    public void testGenerateIdSuccessWithSaltTwo() {
        String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4"; //RCN TOKEN
        String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";

        ERC20 erc20 = new ERC20(tokenContractAddress);
        IntentAction intentAction = erc20.balanceOf(
                new Address(to)
        );

        Credentials credentials = Credentials.create("512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");
        String contractAddress = "0xbbf289d846208c16edc8474705c748aff07732db";
        List<byte[]> dependencies = Arrays.asList(
                hexStringToByteArray("0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e"),
                hexStringToByteArray("0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928")
        );
        Intent intent = IntentBuilder.anIntent()
                .withDependencies(dependencies)
                .withSigner(credentials.getAddress())
                .withWallet(contractAddress)
                .withMinGasLimit(BigInteger.valueOf(300000))
                .withMaxGasPrice(BigInteger.valueOf(999999))
                .withIntentAction(intentAction)
                .withSalt(hexStringToByteArray("0x0000000000000000000000000000000000000000000000000000000000000002"))
                .build();

        Assert.assertEquals(toHexString(intent.getId()), "0xacd5d801cecc1790b95c5395e4f48a40d964ae0c6b70051b3c907060e67da079");
    }

    /*
        Fail -> Test without signer.
     */
    @Test(expected = ValidationException.class)
    public void testGenerateIdFailWithoutSigner() {
        String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4"; //RCN TOKEN
        String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";

        ERC20 erc20 = new ERC20(tokenContractAddress);
        IntentAction intentAction = erc20.balanceOf(
                new Address(to)
        );

        String contractAddress = "0xbbf289d846208c16edc8474705c748aff07732db";
        List<byte[]> dependencies = Arrays.asList(
                hexStringToByteArray("0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e"),
                hexStringToByteArray("0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928")
        );
        IntentBuilder.anIntent()
                .withDependencies(dependencies)
                .withWallet(contractAddress)
                .withMinGasLimit(BigInteger.valueOf(300000))
                .withMaxGasPrice(BigInteger.valueOf(999999))
                .withIntentAction(intentAction)
                .build();
    }

    /*
        Fail -> Test without wallet.
     */
    @Test(expected = ValidationException.class)
    public void testGenerateIdFailWithoutWallet() {
        String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4"; //RCN TOKEN
        String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";

        ERC20 erc20 = new ERC20(tokenContractAddress);
        IntentAction intentAction = erc20.balanceOf(
                new Address(to)
        );

        Credentials credentials = Credentials.create("512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");
        List<byte[]> dependencies = Arrays.asList(
                hexStringToByteArray("0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e"),
                hexStringToByteArray("0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928")
        );
        IntentBuilder.anIntent()
                .withDependencies(dependencies)
                .withSigner(credentials.getAddress())
                .withMinGasLimit(BigInteger.valueOf(300000))
                .withMaxGasPrice(BigInteger.valueOf(999999))
                .withIntentAction(intentAction)
                .build();
    }

    /*
        Fail -> Test without intentAction.
     */
    @Test(expected = ValidationException.class)
    public void testGenerateIdFailWithoutIntentAction() {

        Credentials credentials = Credentials.create("512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");
        String contractAddress = "0xbbf289d846208c16edc8474705c748aff07732db";
        List<byte[]> dependencies = Arrays.asList(
                hexStringToByteArray("0xee2e1b62b008e27a5a3d66352f87e760ed85e723b6834e622f38b626090f536e"),
                hexStringToByteArray("0x6b67aac6eda8798297b1591da36a215bfbe1fed666c4676faf5a214d54e9e928")
        );
        IntentBuilder.anIntent()
                .withDependencies(dependencies)
                .withSigner(credentials.getAddress())
                .withWallet(contractAddress)
                .withMinGasLimit(BigInteger.valueOf(300000))
                .withMaxGasPrice(BigInteger.valueOf(999999))
                .build();
    }



}
