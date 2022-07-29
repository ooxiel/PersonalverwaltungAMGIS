package com.AMGIS.Files;

import java.nio.file.Path;

public class Anlagen {

    private String fileName;

    private Path filePath;

    public Anlagen(String fileName, Path filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }
}
