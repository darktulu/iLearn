package com.simu.ilearn.server.util.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("fileBackup")
public class FileBackup {
    @Autowired
    private FileService fileService;

    @ServiceActivator
    public FileMetaData process(byte[] data, @Header(FileHeaders.FILENAME) String fileName) throws IOException {
        return fileService.storeFile(data, fileName);
    }
}
