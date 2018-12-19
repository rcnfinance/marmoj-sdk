# marmoj-sdk
[![CircleCI](https://circleci.com/gh/ripio/marmoj-sdk.svg?style=shield)](https://circleci.com/gh/ripio/marmoj-sdk)

```java
String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4";
String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";
ERC20Impl erc20 = new ERC20Impl(tokenContractAddress);

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

SignedIntent sign = MarmoUtils.sign(intent, credentials);

intentService.send(sign);
```
