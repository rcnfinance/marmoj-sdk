package network.marmoj.service;

import network.marmoj.client.IntentClient;
import network.marmoj.model.core.SignedIntent;
import network.marmoj.model.request.IntentRequest;
import network.marmoj.model.response.IntentResponse;
import network.marmoj.transformer.IntentRequestTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntentServiceImpl implements IntentService {

    @Autowired
    private IntentClient intentClient;

    @Override
    public IntentResponse send(SignedIntent intent) {
        IntentRequest request = IntentRequestTransformer.transform(intent);
        return intentClient.post(request);
    }

}
