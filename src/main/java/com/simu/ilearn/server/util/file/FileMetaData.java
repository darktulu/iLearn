package com.simu.ilearn.server.util.file;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.Date;

public class FileMetaData {
    private String uuid;
    private String fileName;
    private String signature;
    private Date created;
    private Date updated;

    public void calculateSignature(byte[] data) {
        HashFunction hf = Hashing.md5();
        HashCode hc = hf.newHasher()
                .putBytes(data)
                .hash();
        signature = hc.toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
