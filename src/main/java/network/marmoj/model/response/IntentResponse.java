package network.marmoj.model.response;

import io.netty.handler.codec.http.HttpResponseStatus;

public class IntentResponse {

    private HttpResponseStatus status;

    public IntentResponse(HttpResponseStatus status) {
        this.status = status;
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    public void setStatus(HttpResponseStatus status) {
        this.status = status;
    }
}
