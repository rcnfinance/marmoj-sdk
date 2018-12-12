package network.marmoj.client;

import network.marmoj.model.request.IntentRequest;
import network.marmoj.model.response.IntentResponse;

public interface IntentClient {

    IntentResponse post(IntentRequest intent);

}
