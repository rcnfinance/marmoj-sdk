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

        Assert.assertEquals(toHexString(intent.getId()), "0x7935c8f49cb284e1c5c8dd95b3fc6c9cad6519a17555a5f2e43f9aaa31d25a37");
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

        Assert.assertEquals(toHexString(intent.getId()), "0x0dd96a883c69dca2fef7de903ed543b2751919592a799902aa84ce7ed6a23479");
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

        Assert.assertEquals(toHexString(intent.getId()), "0x5de183da65683636ad564c80559c6cf68d5c738239f15da75e5a020d039cf7fb");
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

        Assert.assertEquals(toHexString(intent.getId()), "0x0d42d9890e1c0cca4d56ec5b532e6f7f1597f5cda57a0c1726f0eb25d2bc4a26");
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

        Assert.assertEquals(toHexString(intent.getId()), "0x40b7b0871f7b3e25020766c21545be0ef33349a949b6f4b9548387d4d539a110");
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

        Assert.assertEquals(toHexString(intent.getId()), "0x63bfa4961085e360ff2507256aae202ef05fe1883475eb21456796b81f5a0e58");
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
        - Salt (0x0000000000000000000000000000000000000000000000000000000000000002)
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

        Assert.assertEquals(toHexString(intent.getId()), "0x6e78ee9f136303375275ad50c6f0823f5863a148d351552409685a8b491d3a98");
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
