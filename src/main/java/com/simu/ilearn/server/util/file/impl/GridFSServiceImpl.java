package com.simu.ilearn.server.util.file.impl;

import com.simu.ilearn.server.util.file.FileMetaData;
import com.simu.ilearn.server.util.file.FileService;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Component
public class GridFSServiceImpl implements FileService {
    @Autowired
    private GridFsOperations operations;
    @Autowired
    private MappingMongoConverter converter;

    @Override
    public FileMetaData storeFile(byte[] data, String filename) throws IOException {
        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.calculateSignature(data);
        fileMetaData.setUuid(UUID.randomUUID().toString());
        fileMetaData.setCreated(new Date());
        fileMetaData.setFileName(filename);

        FileMetaData exist = loadFileBySignature(fileMetaData.getSignature());
        if (exist == null) {
            InputStream in = new ByteArrayInputStream(data);
            operations.store(in, filename, fileMetaData);
            return fileMetaData;
        } else {
            return exist;
        }
    }

    @Override
    public InputStream loadFileById(String id) {
        GridFSDBFile file = operations.findOne(new Query(Criteria.where("metadata.uuid").is(id)));
        return file.getInputStream();
    }

    @Override
    public FileMetaData loadFileMetadataById(String id) {
        GridFSDBFile file = operations.findOne(new Query(Criteria.where("metadata.uuid").is(id)));
        if (file != null) {
            FileMetaData metaData = converter.read(FileMetaData.class, file.getMetaData());
            return metaData;
        }

        return null;
    }

    private FileMetaData loadFileBySignature(String hash) {
        GridFSDBFile file = operations.findOne(new Query(Criteria.where("md5").is(hash)));

        if (file != null) {
            FileMetaData metaData = converter.read(FileMetaData.class, file.getMetaData());
            return metaData;
        }

        return null;
    }
}
