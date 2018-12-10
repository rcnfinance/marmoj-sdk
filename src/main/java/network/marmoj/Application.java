package network.marmoj;

import network.marmoj.builder.IntentBuilder;
import network.marmoj.model.contracts.MarmoCore;
import network.marmoj.model.core.Intent;
import network.marmoj.service.MarmoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private MarmoService<MarmoCore> service;

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {

        service.setup("https://ropsten.node.rcn.loans:8545", "512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");

        byte[] bytes = Numeric.toBytesPadded(BigInteger.ZERO, 32);
        ArrayList<byte[]> dependencies = new ArrayList<>();
        dependencies.add(bytes);
        Intent intent = IntentBuilder.anIntent()
                .withData(bytes)
                .withFrom(service.getCredentials().getAddress())
                .withDependencies(dependencies)
                .withTo("0x7F5EB5bB5cF88cfcEe9613368636f458800e62CB")
                .withMaxGasPrice(BigInteger.valueOf(999999999))
                .withMinGasLimit(BigInteger.ZERO)
                .withSalt(Numeric.toBytesPadded(BigInteger.ONE, 32))
                .withValue(BigInteger.ONE)
                .build();

        byte[] result = service.encodeTransactionData("0xe038d8fdfab5c8a0c9178dbe9e730b3ab1100c52", intent);
        LOGGER.info("Result: " + result.toString());
    }


}