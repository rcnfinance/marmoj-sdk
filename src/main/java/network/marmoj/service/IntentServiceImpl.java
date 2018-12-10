package network.marmoj.service;

import network.marmoj.client.IntentClient;
import network.marmoj.model.core.Intent;
import network.marmoj.model.response.IntentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntentServiceImpl implements IntentService {

    @Autowired
    private IntentClient intentClient;

    @Override
    public IntentResponse send(Intent intent) {
        return intentClient.post(intent);
    }

}
