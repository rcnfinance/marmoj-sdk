package network.marmoj.client.request;

import org.web3j.utils.Numeric;

import static org.web3j.crypto.Sign.SignatureData;

public class SignatureDataRequest {
    private String r;
    private String s;
    private String v;

    public SignatureDataRequest(SignatureData signature) {
        this.r = Numeric.toHexString(signature.getR());
        this.s = Numeric.toHexString(signature.getS());
        this.v = Numeric.toHexString(new byte[]{signature.getV()});
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
