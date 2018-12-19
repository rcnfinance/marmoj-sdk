# marmoj-sdk
[![CircleCI](https://circleci.com/gh/ripio/marmoj-sdk.svg?style=shield)](https://circleci.com/gh/ripio/marmoj-sdk)

# Description
Marmoj-sdk helps to developers java/android to consume marmo-relayer.

# Simple Summary
Allowing users to sign messages to show intent of execution, but allowing a third party relayer (https://github.com/ripio/marmo-relayer) to execute them is an emerging pattern being. This pattern simplifies the integration with any Ethereum based platform.

# Abstract
## User pain points:
- users don't want to think about ether
- users want to be able to pay for transactions using what they 
- Users don’t want to download apps/extensions (at least on the desktop) to connect to their apps

# Ecosystem Graph
![](./images/01.png)


# Examples
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
