package com.infor.util;

import com.infor.exception.FileWithoutExtensionException;

import java.io.File;
import java.nio.file.Path;

public class FileUtils {

    public static String getFileExtension(String filePath){
        Path path = Path.of(filePath);
        String fileName = path.getFileName().toString();
        String fileNames[] = fileName.split("\\.");
        if (fileNames.length != 2){
            throw new FileWithoutExtensionException(fileName +" no extension");
        }
        String extension = fileNames[1];
        return extension;

    }

    public static String getFileDirectoryPath(String filePath){
        Path path = Path.of(filePath);
        Path parentPath = path.getParent();
        return parentPath.toString();
    }

    public static String getOutputPath(String inputFile){
        Path inputPath = Path.of(inputFile);
        inputPath = inputPath.toAbsolutePath().getParent();
        return inputPath.toString();
    }

    public boolean checkFileExist(String filePath){
        File file = new File(filePath);
        return file.exists();
    }



}
