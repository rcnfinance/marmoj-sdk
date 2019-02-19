package network.marmoj.model;

import java.math.BigInteger;
import network.marmoj.model.data.domain.Function;

public class IntentAction {

  private String to;
  private String data = "0x0";
  private BigInteger value;
  private Function parent;

  public IntentAction(String to, BigInteger value) {
    this.to = to;
    this.value = value;
  }

  public IntentAction(String to, String data, BigInteger value, Function parent) {
    this(to, value);
    this.data = data;
    this.parent = parent;
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

  public Function getParent() {
    return parent;
  }

  public void setParent(Function parent) {
    this.parent = parent;
  }
}