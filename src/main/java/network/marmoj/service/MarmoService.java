package network.marmoj.service;

import network.marmoj.model.core.Intent;
import org.web3j.crypto.Credentials;
import org.web3j.tx.Contract;

public interface MarmoService<C extends Contract> {

    boolean setup(String nodeAddress, String privateKey);

    byte[] encodeTransactionData(String contractAddress, Intent intent) throws Exception;

    Credentials getCredentials();
}
