package com.infor.util;

import com.infor.exception.UnsupportedFileExtensionException;
import com.infor.util.transformer.AbstractFileTransformer;
import com.infor.util.transformer.TextFileTransformer;
import com.infor.util.transformer.XMLFileTransformer;

import java.io.File;

public class FileTransformerFactory {

    public static AbstractFileTransformer createXMLFileTransformer(String inputFile, String outputFile) {
        File input = new File(inputFile);
        File output = new File(outputFile);

        return new XMLFileTransformer(input, output);
    }

    public static AbstractFileTransformer createXMLFileTransformer(String extension, File inputFile, File outputFile) {
        return new XMLFileTransformer(inputFile, outputFile);
    }

    public static AbstractFileTransformer createTextFileTransformer(String inputFile, String outputFile) {
        File input = new File(inputFile);
        File output = new File(outputFile);

        return new TextFileTransformer(input, output);
    }


    public static AbstractFileTransformer createTextFileTransformer(String extension, String inputFile, String outputFile) {
        File input = new File(inputFile);
        File output = new File(outputFile);

        return new TextFileTransformer(input, output);
    }

    public static AbstractFileTransformer createFileTransformer(String inputFile, String outputFile) {
        File input = new File(inputFile);
        File output = new File(outputFile);

        String[] extentions = inputFile.split(".");
        String extension = extentions[extentions.length - 1];
        if ("xml".equals(extension)) {
            return new XMLFileTransformer(input, output);
        } else {
            return new TextFileTransformer(input, output);
        } //TODO add file extension not supported
    }


    public static AbstractFileTransformer createFileTransformer(String inputFile) {
        File input = new File(inputFile);

        String extension = FileUtils.getFileExtension(inputFile);
        String directoryPath = FileUtils.getFileDirectoryPath(inputFile);

        String outputFile = directoryPath + "/result."+extension;
        File output = new File(outputFile);
        if ("xml".equals(extension)) {

            return new XMLFileTransformer(input, output);
        } else if ("txt".equals(extension)){
            return new TextFileTransformer(input, output);
        } else {
            throw new UnsupportedFileExtensionException("Extension ." + extension + " unsupported");
        }
    }

    public static AbstractFileTransformer createFileTransformer(String inputFile,String searchText,String replaceText) {
        File input = new File(inputFile);

        String extension = FileUtils.getFileExtension(inputFile);
        String directoryPath = FileUtils.getFileDirectoryPath(inputFile);

        String outputFile = directoryPath + "\\result."+extension;
        File output = new File(outputFile);

        if ("xml".equals(extension)) {
            return new XMLFileTransformer(input, output,searchText,replaceText);
        } else if ("txt".equals(extension)){
            return new TextFileTransformer(input, output,searchText,replaceText);
        } else {
            throw new UnsupportedFileExtensionException("Extension ." + extension + " unsupported");
        }
    }
    public static AbstractFileTransformer createFileTransformer(String inputFile,String outputFile,String searchText,String replaceText) {
        File input = new File(inputFile);

        String extension = FileUtils.getFileExtension(inputFile);
        File output = new File(outputFile);

        if ("xml".equals(extension)) {
            return new XMLFileTransformer(input, output,searchText,replaceText);
        } else if ("txt".equals(extension)){
            return new TextFileTransformer(input, output,searchText,replaceText);
        } else {
            throw new UnsupportedFileExtensionException("Extension ." + extension + " unsupported");
        }
    }
}
