package network.marmoj.client;

import network.marmoj.model.SignedIntent;
import network.marmoj.client.response.IntentResponse;

public interface IRelayClient {

    IntentResponse post(SignedIntent intent);

}
