package network.marmoj.model;

import java.math.BigInteger;

public class IntentAction {

  public String to;
  public String data;
  public BigInteger value;

  public IntentAction(String to, String data, BigInteger value) {
    this.to = to;
    this.data = data;
    this.value = value;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public BigInteger getValue() {
    return value;
  }

  public void setValue(BigInteger value) {
    this.value = value;
  }
}