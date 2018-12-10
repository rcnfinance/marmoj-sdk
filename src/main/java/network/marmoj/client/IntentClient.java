package network.marmoj.client;

import network.marmoj.model.core.Intent;
import network.marmoj.model.response.IntentResponse;

public interface IntentClient {
    IntentResponse post(Intent intent);
}
