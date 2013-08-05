package com.simu.ilearn.common.client.widget;

public enum FileType {
    IMAGE("image/"),
    ZIP("application/zip"),
    TEXT("text/");

    private String mimeType;

    private FileType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }
}
