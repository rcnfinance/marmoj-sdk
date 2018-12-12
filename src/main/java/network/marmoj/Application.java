package network.marmoj;

import network.marmoj.model.core.Intent;
import network.marmoj.model.core.SignedIntent;
import network.marmoj.service.MarmoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private MarmoService service;

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {

        service.setup("https://ropsten.node.rcn.loans:8545", "0xe038d8fdfab5c8a0c9178dbe9e730b3ab1100c52", "512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");


        final Function transfer = new Function("transfer",
                Arrays.asList(
                        new Address("0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB"),
                        new Address("0x6684C2F982758685780b8d488c32fAfA4d008A53"),
                        new Uint256(1)),
                Arrays.asList(new TypeReference<Bool>() {}));

        Intent intent = service.create(
                new ArrayList<>(),
                "0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB",
                BigInteger.ONE,
                FunctionEncoder.encode(transfer),
                BigInteger.ZERO,
                BigInteger.valueOf(999999999),
                null
                );

        SignedIntent signedIntent = service.sign(intent);



        LOGGER.info("Result: " + signedIntent.toString());
    }


}