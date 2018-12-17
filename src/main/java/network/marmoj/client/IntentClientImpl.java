package network.marmoj.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpResponseStatus;
import network.marmoj.model.request.IntentRequest;
import network.marmoj.model.response.IntentResponse;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.asynchttpclient.Dsl.asyncHttpClient;

@Component
public class IntentClientImpl implements IntentClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntentClientImpl.class);
    public static final String PATH = "http://10.148.108.93:8081/relay/";

    @Override
    public IntentResponse post(IntentRequest intent) {

        try (AsyncHttpClient asyncHttpClient = asyncHttpClient()) {

            ObjectMapper mapper = new ObjectMapper();
            String body = mapper.writeValueAsString(intent);
            LOGGER.info(body);

            asyncHttpClient
                    .preparePost(PATH)
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

