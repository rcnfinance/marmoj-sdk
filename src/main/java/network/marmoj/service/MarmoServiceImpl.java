package network.marmoj.service;

import network.marmoj.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

import java.io.IOException;

@Service
public class MarmoServiceImpl implements MarmoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;

    @Override
    public void connect() throws IOException {
        LOGGER.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());

        LOGGER.info("Credentials loaded: " + credentials.getAddress());
    }
}
