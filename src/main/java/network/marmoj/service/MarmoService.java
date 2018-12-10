package network.marmoj.service;

import org.web3j.crypto.Credentials;
import org.web3j.tx.Contract;

public interface MarmoService<C extends Contract> {

    Credentials getCredentials();

}
