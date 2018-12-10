package network.marmoj.service;

import network.marmoj.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;

@Service
public class MarmoServiceImpl<C extends Contract> implements MarmoService<C> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;

    public Credentials getCredentials() {
        LOGGER.info("Credentials loaded: " + credentials.getAddress());
        return credentials;
    }

}
