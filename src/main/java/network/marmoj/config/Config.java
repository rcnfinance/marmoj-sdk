package network.marmoj.config;

public class Config {
    private String initCode;
    private String marmoSork;

    private static Config instance;


    private Config(){}

    public static Config newInstance(String initCode, String marmoSork) {
        instance = new Config();
        instance.setInitCode(initCode);
        instance.setMarmoSork(marmoSork);
        return instance;
    }

    public static Config getInstance() {
        if(instance == null){
            instance = new Config();
        }
        instance.setInitCode("0x98ef25e9f596000233ed019f909cc8a5f35984f1cc0b0b9e05407ce7a6820bc1");
        instance.setMarmoSork("0x6306b6a26c70c03279c037f630be03046104cb37");
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
