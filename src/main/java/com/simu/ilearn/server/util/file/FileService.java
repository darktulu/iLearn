package com.simu.ilearn.server.util.file;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    FileMetaData storeFile(byte[] data, String filename) throws IOException;

    InputStream loadFileById(String id);

    FileMetaData loadFileMetadataById(String id);
}
