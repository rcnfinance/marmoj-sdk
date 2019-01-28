package network.marmoj;

import static org.web3j.crypto.Sign.SignatureData;

import io.netty.handler.codec.http.HttpResponseStatus;
import network.marmoj.client.RelayClient;
import network.marmoj.client.response.IntentResponse;
import network.marmoj.config.Provider;
import network.marmoj.model.IntentStatus;
import network.marmoj.model.IntentWallet;

public class SignedIntent {

  private byte[] id;
  private Intent intent;
  private SignatureData signature;
  private IntentWallet wallet;

  public SignedIntent(byte[] id, Intent intent, SignatureData signature, IntentWallet wallet) {
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

  public IntentWallet getWallet() {
    return wallet;
  }

  public boolean relay(Provider provider) {
    RelayClient relayClient = new RelayClient(provider.getRelayer());
    IntentResponse response = relayClient.post(this);
    return HttpResponseStatus.OK.equals(response.getStatus());
  }

  public IntentStatus status(Provider provider) {
    return null;
  }


}
