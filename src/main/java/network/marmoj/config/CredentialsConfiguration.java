package network.marmoj.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;

@Configuration
public class CredentialsConfiguration {

    private static Log LOGGER = LogFactory.getLog(CredentialsConfiguration.class);

    @Value("${wallet.private-key}")
    private String privateKey;

    @Bean
    public Credentials getCredentials() {
        return Credentials.create(privateKey);
    }

}