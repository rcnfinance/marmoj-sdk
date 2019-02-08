package network.marmoj.model;

public class IntentDependency implements Dependency {
  private String address;
  private byte[] id;

  public IntentDependency(byte[] id, String address) {
    this.id = id;
    this.address = address;
  }

  @Override
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public byte[] getId() {
    return id;
  }

  public void setId(byte[] id) {
    this.id = id;
  }
}
