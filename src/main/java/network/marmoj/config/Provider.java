package network.marmoj.config;

import org.web3j.protocol.Web3j;

public class Provider {

    private String relayer;
    private Web3j web3;

    public String getRelayer() {
        return relayer;
    }

    public void setRelayer(String relayer) {
        this.relayer = relayer;
    }

    public Web3j getWeb3() {
        return web3;
    }

    public void setWeb3(Web3j web3) {
        this.web3 = web3;
    }
}

