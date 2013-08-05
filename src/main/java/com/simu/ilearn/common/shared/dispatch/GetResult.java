package com.simu.ilearn.common.shared.dispatch;

public class GetResult<T> extends Response {
    private T payload;

    public GetResult() {
    }

    public GetResult(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
