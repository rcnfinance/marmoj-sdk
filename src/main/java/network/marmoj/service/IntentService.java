package network.marmoj.service;

import network.marmoj.model.core.SignedIntent;
import network.marmoj.model.response.IntentResponse;

public interface IntentService {

    IntentResponse send(SignedIntent intent);

}
