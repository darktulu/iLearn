package com.simu.ilearn.common.shared.dispatch;

import com.gwtplatform.dispatch.shared.Result;

public class Response implements Result {
    protected String exceptionMessage;

    public Response() {
    }

    public Response(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
