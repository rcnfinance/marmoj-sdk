package network.marmoj;

import static org.web3j.crypto.Sign.SignatureData;

import io.netty.handler.codec.http.HttpResponseStatus;
import network.marmoj.client.RelayClient;
import network.marmoj.client.response.IntentResponse;
import network.marmoj.config.Provider;
import network.marmoj.exception.ValidationException;
import network.marmoj.model.IntentStatus;
import network.marmoj.model.Wallet;

public class SignedIntent {

  private byte[] id;
  private Intent intent;
  private SignatureData signature;
  private Wallet wallet;

  public SignedIntent(byte[] id, Intent intent, SignatureData signature, Wallet wallet) {
    this.id = id;
    this.intent = intent;
    this.signature = signature;
    this.wallet = wallet;
  }

  public byte[] getId() {
    return id;
  }

  public Intent getIntent() {
    return intent;
  }

  public SignatureData getSignature() {
    return signature;
  }

  public Wallet getWallet() {
    return wallet;
  }

  public boolean relay() {
    return this.relay(Provider.getGlobal());
  }

  public boolean relay(Provider provider) {
    if (provider == null) {
      return false;
    }
    RelayClient relayClient = new RelayClient(provider.getRelayer());
    IntentResponse response = relayClient.post(this);
    return HttpResponseStatus.OK.equals(response.getStatus());
  }

  public IntentStatus status(Provider provider) {

    if (provider == null) {
      throw new ValidationException("The provider can not be null or undefined");
    }
    //TODO: MAKE IMPLEMENTATION
    return null;
  }

}
