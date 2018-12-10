package network.marmoj;

import network.marmoj.builder.IntentBuilder;
import network.marmoj.model.contracts.MarmoCore;
import network.marmoj.model.core.Intent;
import network.marmoj.service.MarmoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.System.exit;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private MarmoService<MarmoCore> service;

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {

        service.setup("https://ropsten.infura.io/v3/553aebb642dd4f529c70cec765c43840", "512850c7ebe3e1ade1d0f28ef6eebdd3ba4e78748e0682f8fda6fc2c2c5b334a");

        Intent intent = IntentBuilder.anIntent()
                .withData("0".getBytes())
                .withFrom(service.getCredentials().getAddress())
                .withId(Numeric.hexStringToByteArray(asciiToHex("1")))
                .withDependencies(new ArrayList<>())
                .withTo("0x6684C2F982758685780b8d488c32fAfA4d008A53")
                .withMaxGasPrice(BigInteger.valueOf(999999999))
                .withMinGasLimit(BigInteger.ZERO)
                .withSalt(Numeric.hexStringToByteArray(asciiToHex("0")))
                .withValue(BigInteger.ONE)
                .build();


        service.encodeTransactionData("0x692a70d2e424a56d2c6c27aa97d1a86395877b3a", intent);
        exit(0);
    }

    public String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString() + "".join("", Collections.nCopies(32 - (hex.length()/2), "00"));
    }
}