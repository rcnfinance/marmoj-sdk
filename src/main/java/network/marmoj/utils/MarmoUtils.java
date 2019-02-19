package network.marmoj.utils;

import java.math.BigDecimal;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.web3j.utils.Numeric;

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

  public static byte[] sanitizePrefix(byte[] salt) {
    return Numeric.hexStringToByteArray(sanitizePrefix(Numeric.toHexString(salt)));
  }

  public static String toHexStringZeroPadded(String value) {
    value = sanitizePrefix(value);
    StringBuilder out = new StringBuilder();
    for (int i = 0; i < 64 - value.length(); i++) {
      out.append(0);
    }
    out.append(value);
    return out.toString();
  }

}
