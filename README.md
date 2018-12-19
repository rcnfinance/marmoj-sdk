# Marmoj-sdk
[![CircleCI](https://circleci.com/gh/ripio/marmoj-sdk.svg?style=shield)](https://circleci.com/gh/ripio/marmoj-sdk)


# Description
Marmoj-sdk helps to developers java/android to consume Marmo Relayer.

# Simple Summary
Allowing users to sign messages to show intent of execution, but allowing a third party relayer (https://github.com/ripio/marmo-relayer) 
to execute them is an emerging pattern being. This pattern simplifies the integration with any Ethereum based platform.

# Abstract
### User pain points:
- users don't want to think about ether
- users want to be able to pay for transactions using what they 
- Users don’t want to download apps/extensions (at least on the desktop) to connect to their apps

# Ecosystem Graph
![](./images/01.png)

# Table of Contents
-TODO

# Features
- Complete implementation of Intent for Marmo relay.
- Ethereum wallet support.
- Comprehensive integration tests demonstrating a number of the above scenarios.
- Android compatible.

##### It has four runtime dependencies:

- web3j (https://github.com/web3j).
- async-http-client (https://github.com/AsyncHttpClient/async-http-client).
- logback (https://github.com/qos-ch/logback).
- Jackson for JSON serialisation (https://github.com/FasterXML/jackson).

# Getting started

##### Prerequisites
* Java 8
* Maven

Typically your application should depend on release versions of marmoj, but you may also use snapshot dependencies
for early access to features and fixes, refer to the  `Snapshot Dependencies`_ section.

| Add the relevant dependency to your project:

##### Maven

Java 8:

```xml
   <dependency>
     <groupId>...</groupId>
     <artifactId>...</artifactId>
     <version>...</version>
   </dependency>
```

##### Gradle

Java 8:

```yaml
  compile ('...:...:...')
```
   
# Examples
```java
String tokenContractAddress = "0x2f45b6fb2f28a73f110400386da31044b2e953d4"; //RCN TOKEN
String to = "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB";
ERC20 erc20 = new ERC20(tokenContractAddress); //

int value = 1;
IntentAction intentAction = erc20.transfer(
        new Address(to),
        new Uint256(value)
);

Credentials credentials = Credentials.create("Your credentials");

String contractAddress = "0x7c0e959d9b7c82d47c5013832f89282b8b773393";

// Intents ids dependency
List<byte[]> dependencias = Arrays.asList(
        Numeric.hexStringToByteArray("IntentId1"),
        Numeric.hexStringToByteArray("IntentId2"),
        Numeric.hexStringToByteArray("IntentIdN")
);

Intent intent = IntentBuilder.anIntent()
        .withDependencies(dependencias)
        .withSigner(credentials.getAddress())
        .withWallet(contractAddress)
        .withIntentAction(intentAction)
        .build();

SignedIntent sign = MarmoUtils.sign(intent, credentials);

IRelayClient client = new RelayClient("http://ec2-3-16-37-20.us-east-2.compute.amazonaws.com/relay"); //relay url
client.send(sign);
```
