package network.marmoj.model;

public class IntentStatus {

  private IntentStatusCode code;
  private IntentReceipt receipt;

  public IntentStatusCode getCode() {
    return code;
  }

  public void setCode(IntentStatusCode code) {
    this.code = code;
  }

  public IntentReceipt getReceipt() {
    return receipt;
  }

  public void setReceipt(IntentReceipt receipt) {
    this.receipt = receipt;
  }
}
