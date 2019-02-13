package network.marmoj;

import java.math.BigInteger;
import java.util.List;
import network.marmoj.model.Dependency;

public class Intent {

  List<Dependency> dependencies;
  private String salt;
  private BigInteger expiration;
  /* For Transactions */
  private String to;
  private BigInteger value;
  private String data;
  private BigInteger maxGasLimit;
  private BigInteger maxGasPrice;

  public Intent(List<Dependency> dependencies, String salt, String to, BigInteger expiration,
      BigInteger value,
      String data, BigInteger maxGasLimit, BigInteger maxGasPrice) {
    this.dependencies = dependencies;
    this.salt = salt;
    this.expiration = expiration;
    this.to = to;
    this.value = value;
    this.data = data;
    this.maxGasLimit = maxGasLimit;
    this.maxGasPrice = maxGasPrice;
  }

  public List<Dependency> getDependencies() {
    return dependencies;
  }

  public BigInteger getExpiration() {
    return expiration;
  }

  public String getSalt() {
    return salt;
  }

  public String getTo() {
    return to;
  }

  public BigInteger getValue() {
    return value;
  }

  public String getData() {
    return data;
  }

  public BigInteger getMaxGasLimit() {
    return maxGasLimit;
  }

  public BigInteger getMaxGasPrice() {
    return maxGasPrice;
  }

}
