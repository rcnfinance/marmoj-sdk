package network.marmoj.client;

import network.marmoj.model.core.Intent;
import network.marmoj.model.response.IntentResponse;
import org.springframework.stereotype.Component;

@Component
public class IntentClientImpl implements IntentClient {

    @Override
    public IntentResponse post(Intent intent) {
        return new IntentResponse();
    }

}
