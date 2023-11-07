package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
    private int fileId;
    private String fileName;
    private String fileType;
    private long fileSize;
    private int userId;
    private byte[] fileData;

    // Constructors, getters, and setters

    public File() {
    }

    public File(int fileId, String fileName, String fileType, long fileSize, int userId, byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public File(Integer integer, String fileName, String fileType, long fileSize, int userId, byte[] fileData) {
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}