package network.marmoj.service;

import network.marmoj.model.core.Intent;
import network.marmoj.model.response.IntentResponse;

public interface IntentService {

    IntentResponse send(Intent intent);

}
