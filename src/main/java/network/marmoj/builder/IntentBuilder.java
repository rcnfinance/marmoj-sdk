package network.marmoj.builder;

import network.marmoj.model.core.Intent;

public final class IntentBuilder {

    private String id;
    private String signature;
    private String from;
    private String to;
    private String value;
    private String callData;

    private IntentBuilder() {
    }

    public static IntentBuilder anIntent() {
        return new IntentBuilder();
    }

    public IntentBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public IntentBuilder withSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public IntentBuilder withFrom(String from) {
        this.from = from;
        return this;
    }

    public IntentBuilder withTo(String to) {
        this.to = to;
        return this;
    }

    public IntentBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public IntentBuilder withCallData(String callData) {
        this.callData = callData;
        return this;
    }

    public Intent build() {
        Intent intent = new Intent();
        intent.setId(id);
        intent.setSignature(signature);
        intent.setFrom(from);
        intent.setTo(to);
        intent.setValue(value);
        intent.setCallData(callData);
        return intent;
    }
}
