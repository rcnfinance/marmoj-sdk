package network.marmoj.config;

import network.marmoj.config.enums.Network;

public class Config {
    private String initCode;
    private String marmoSork;

    private static Config instance;

    private Config(){}

    public static Config newInstance(Network network) {
        instance = new Config();
        return newInstance(network.getInitCode(), network.getMarmoStork());
    }

    public static Config newInstance(String initCode, String marmoSork) {
        instance = new Config();
        instance.setInitCode(initCode);
        instance.setMarmoSork(marmoSork);
        return instance;
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
