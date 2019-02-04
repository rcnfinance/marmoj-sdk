package network.marmoj.model;

import java.math.BigInteger;

public class IntentReceipt {

  private String txHash;
  private String relayer;
  private BigInteger blockNumber;
  private Boolean success;
  private BigInteger confirmation;

  public String getTxHash() {
    return txHash;
  }

  public void setTxHash(String txHash) {
    this.txHash = txHash;
  }

  public String getRelayer() {
    return relayer;
  }

  public void setRelayer(String relayer) {
    this.relayer = relayer;
  }

  public BigInteger getBlockNumber() {
    return blockNumber;
  }

  public void setBlockNumber(BigInteger blockNumber) {
    this.blockNumber = blockNumber;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public BigInteger getConfirmation() {
    return confirmation;
  }

  public void setConfirmation(BigInteger confirmation) {
    this.confirmation = confirmation;
  }
}
