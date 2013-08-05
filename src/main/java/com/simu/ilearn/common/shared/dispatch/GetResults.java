package com.simu.ilearn.common.shared.dispatch;

import java.util.List;

public class GetResults<T> extends Response {
    private List<T> payload;

    public GetResults() {
    }

    public GetResults(List<T> payload) {
        this.payload = payload;
    }

    public List<T> getPayload() {
        return payload;
    }

    public void setPayload(List<T> payload) {
        this.payload = payload;
    }
}
