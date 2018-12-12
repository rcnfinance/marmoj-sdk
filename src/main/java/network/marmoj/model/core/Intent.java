package network.marmoj.model.core;

import java.util.Arrays;
import java.util.List;

public class  Intent {

    private byte[] id;
    List<byte[]> dependencies;
    private String wallet;
    private IntentTx tx;
    private byte[] salt;

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public List<byte[]> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<byte[]> dependencies) {
        this.dependencies = dependencies;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public IntentTx getTx() {
        return tx;
    }

    public void setTx(IntentTx tx) {
        this.tx = tx;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Intent{");
        sb.append("id=").append(Arrays.toString(id));
        sb.append(", dependencies=").append(dependencies);
        sb.append(", wallet='").append(wallet).append('\'');
        sb.append(", tx=").append(tx);
        sb.append(", salt=").append(Arrays.toString(salt));
        sb.append('}');
        return sb.toString();
    }
}
