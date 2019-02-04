package network.marmoj.config;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class Provider {

  private String relayer;
  private Web3j web3j;

  public Provider(String relayer, Web3j web3j) {
    this.relayer = relayer;
    this.web3j = web3j;
  }

  public Provider(String relayer, String node) {
    this.relayer = relayer;
    this.web3j = Web3j.build(new HttpService(node));
  }

  public String getRelayer() {
    return relayer;
  }

  public Web3j getWeb3() {
    return web3j;
  }

}

