package network.marmoj.service;

import network.marmoj.model.core.Intent;
import org.web3j.crypto.Credentials;

public interface MarmoService {

    boolean setup(String nodeAddress, String privateKey);

    byte[] encodeTransactionData(String contractAddress, Intent intent) throws Exception;

    Credentials getCredentials();

}
