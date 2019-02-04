package network.marmoj.config;


public class Config {

  private static Config globalConf;

  private String initCode;
  private String marmoSork;

  public Config(String initCode, String marmoSork) {
    this.initCode = initCode;
    this.marmoSork = marmoSork;
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

}

