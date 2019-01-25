package network.marmoj.utils;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;

import static java.util.Arrays.copyOfRange;

public class MarmoUtils {

    public static final int SIZE_PREFIX = 2;
    public static final String PREFIX = "0x";

    public static String keccak256(String data) {
        return keccak256(Numeric.hexStringToByteArray(data));
    }

    public static String keccak256(byte[] data) {
        Keccak.DigestKeccak kecc = new Keccak.Digest256();
        kecc.update(data, BigDecimal.ZERO.intValue(), data.length);
        return sanitizePrefix(Numeric.toHexString(kecc.digest()));
    }

    public static String sanitizePrefix(String data) {
        return data.substring(SIZE_PREFIX);
    }

    public static String create2(byte[] senderAddr, byte[] initCode, byte[] salt) {
        // 1 - 0xff length, 32 bytes - keccak-256
        byte[] data = new byte[1 + senderAddr.length + salt.length + 32];
        data[0] = (byte) 0xff;
        int currentOffset = 1;
        System.arraycopy(senderAddr, 0, data, currentOffset, senderAddr.length);
        currentOffset += senderAddr.length;
        System.arraycopy(salt, 0, data, currentOffset, salt.length);
        currentOffset += salt.length;
        byte[] sha3InitCode = Hash.sha3(initCode);
        System.arraycopy(sha3InitCode, 0, data, currentOffset, sha3InitCode.length);

        return Numeric.toHexString(sha3omit12(data));
    }

    public static byte[] sha3omit12(byte[] input) {
        byte[] hash = Hash.sha3(input);
        return copyOfRange(hash, 12, hash.length);
    }

}
