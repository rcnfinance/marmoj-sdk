package network.marmoj.model;

import java.math.BigInteger;
import network.marmoj.model.data.domain.Function;
import network.marmoj.model.data.domain.StateMutability;

public class IntentAction {

  private String to;
  private String data = "0x0";
  private BigInteger value;
  private Function parent;
  private StateMutability stateMutability;

  public IntentAction(String to, BigInteger value,
      StateMutability stateMutability) {
    this.to = to;
    this.value = value;
    this.stateMutability = stateMutability;
  }

  public IntentAction(String to, String data, BigInteger value, Function parent) {
    this(to, value, parent.getType());
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

  public StateMutability getStateMutability() {
    return this.stateMutability;
  }

  public void setStateMutability(StateMutability stateMutability) {
    this.stateMutability = stateMutability;
  }
}