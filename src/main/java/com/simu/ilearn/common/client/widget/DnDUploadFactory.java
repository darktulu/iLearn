package com.simu.ilearn.common.client.widget;

public interface DnDUploadFactory {
    DnDUpload create(FileType fileType, String servletPath);
}
