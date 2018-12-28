package network.marmoj.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpResponseStatus;
import network.marmoj.model.core.SignedIntent;
import network.marmoj.model.request.IntentRequest;
import network.marmoj.model.response.IntentResponse;
import network.marmoj.transformer.IntentRequestTransformer;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.asynchttpclient.Dsl.asyncHttpClient;

public class RelayClient implements IRelayClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RelayClient.class);
    private final String path;

    public RelayClient(String path) {
        this.path = path;
    }

    public IntentResponse post(SignedIntent intent) {

        try (AsyncHttpClient asyncHttpClient = asyncHttpClient()) {

            ObjectMapper mapper = new ObjectMapper();
            IntentRequest request = IntentRequestTransformer.transform(intent);
            String body = mapper.writeValueAsString(request);
            LOGGER.info("[Post Body -> ]", body);

            asyncHttpClient
                    .preparePost(path)
                    .setBody(body)
                    .execute()
                    .toCompletableFuture()
                    .thenApply(Response::getResponseBody)
                    .thenAccept(System.out::println)
                    .join();

            return new IntentResponse(HttpResponseStatus.OK);
        } catch (IOException e) {
            return new IntentResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

