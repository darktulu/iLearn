package com.simu.ilearn.common.client.rest;

import com.google.common.base.Strings;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.Window;

public class MyRequestBuilder extends RequestBuilder {
    private StringBuffer postData = new StringBuffer();
    private StringBuffer url = new StringBuffer();

    public MyRequestBuilder(String url) {
        super(GET, url);
        this.url.append(url);
        setHeader("Content-type", "application/x-www-form-urlencoded");
    }

    public void appendParam(String key, String value) {
        if (Strings.isNullOrEmpty(postData.toString())) {
            postData.append("?").append(key).append("=").append(value);
        } else {
            postData.append("&").append(key).append("=").append(value);
        }
    }

    public void sendRequest(String app) {
        url.append(GWT.isScript() ? app + "/" : "").append(postData);
        Window.open(url.toString(), "_blank", "");
    }
}
