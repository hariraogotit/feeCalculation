package com.utilities;

/**
 * Created by Hari Rao on 29/04/17.
 */
public enum FileType {

    CSV("csv");

    private String fileType;

    FileType(String fileType){
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}
