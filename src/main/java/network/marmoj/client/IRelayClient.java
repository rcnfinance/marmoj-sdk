package network.marmoj.client;

import network.marmoj.model.core.SignedIntent;
import network.marmoj.model.response.IntentResponse;

public interface IRelayClient {

    IntentResponse post(SignedIntent intent);

}
