package network.marmoj.config;


public class Config {

  private static Config globalConf;

  private String initCode;
  private String marmoSork;
  private String implementation;
  private String depsUtils;
  private Integer network;

  public Config(String initCode, String marmoSork, String implementation, String depsUtils, Integer network) {
    this.initCode = initCode;
    this.marmoSork = marmoSork;
    this.implementation = implementation;
    this.depsUtils = depsUtils;
    this.network = network;
  }

  public void asDefault() {
    globalConf = this;
  }

  public static Config getGlobal() {
    return globalConf;
  }

  public String getInitCode() {
    return initCode;
  }

  public void setInitCode(String initCode) {
    this.initCode = initCode;
  }

  public String getMarmoSork() {
    return marmoSork;
  }

  public void setMarmoSork(String marmoSork) {
    this.marmoSork = marmoSork;
  }

  public String getImplementation() {
    return implementation;
  }

  public void setImplementation(String implementation) {
    this.implementation = implementation;
  }

  public String getDepsUtils() {
    return depsUtils;
  }

  public void setDepsUtils(String depsUtils) {
    this.depsUtils = depsUtils;
  }

  public Integer getNetwork() {
    return network;
  }

  public void setNetwork(Integer network) {
    this.network = network;
  }
}

