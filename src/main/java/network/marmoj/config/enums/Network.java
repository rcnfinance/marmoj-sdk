package network.marmoj.config.enums;

public enum Network {

    MAINNET("",""),
    ROPSTEN("0x98ef25e9f596000233ed019f909cc8a5f35984f1cc0b0b9e05407ce7a6820bc1", "0x6306b6a26c70c03279c037f630be03046104cb37");

    private final String initCode;
    private final String marmoStork;

    Network(String initCode, String marmoStork) {
        this.initCode = initCode;
        this.marmoStork = marmoStork;
    }

    public String getInitCode() {
        return initCode;
    }

    public String getMarmoStork() {
        return marmoStork;
    }
}
