package network.marmoj.model.data.domain;


public enum StateMutability {
  PURE("PURE"),
  VIEW("VIEW"),
  NONPAYABLE("NONPAYABLE"),
  PAYABLE("PAYABLE");

  private final String value;

  StateMutability(String state) {
    this.value = state;
  }

  public String getValue() {
    return value;
  }
}
