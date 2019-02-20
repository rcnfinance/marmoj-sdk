package network.marmoj.client.request;

import java.math.BigInteger;
import network.marmoj.Intent;
import org.web3j.utils.Numeric;

public class IntentTxRequest {

  private String to;
  private BigInteger value;
  private String data;
  private BigInteger minGasLimit;
  private BigInteger maxGasPrice;
  private String stateMutability;

  public IntentTxRequest(Intent intentTx) {
    this.data = intentTx.getData();
    this.minGasLimit = intentTx.getMaxGasLimit();
    this.maxGasPrice = intentTx.getMaxGasPrice();
    this.to = intentTx.getTo();
    this.value = intentTx.getValue();
    this.stateMutability = intentTx.getStateMutability().getValue();
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public BigInteger getValue() {
    return value;
  }

  public void setValue(BigInteger value) {
    this.value = value;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public BigInteger getMinGasLimit() {
    return minGasLimit;
  }

  public void setMinGasLimit(BigInteger minGasLimit) {
    this.minGasLimit = minGasLimit;
  }

  public BigInteger getMaxGasPrice() {
    return maxGasPrice;
  }

  public void setMaxGasPrice(BigInteger maxGasPrice) {
    this.maxGasPrice = maxGasPrice;
  }

  public String getStateMutability() {
    return stateMutability;
  }

  public void setStateMutability(String stateMutability) {
    this.stateMutability = stateMutability;
  }
}
