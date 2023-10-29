package com.infor.util.transformer;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public abstract class AbstractFileTransformer {

    protected File inputFile;
    protected File outputFile;

    protected String searchText;

    protected String replaceText;

    public AbstractFileTransformer() {
    }

    public AbstractFileTransformer(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public AbstractFileTransformer(File inputFile, File outputFile, String searchText, String replaceText) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.searchText = searchText;
        this.replaceText = replaceText;
    }


    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getReplaceText() {
        return replaceText;
    }

    public void setReplaceText(String replaceText) {
        this.replaceText = replaceText;
    }


    public abstract void transformFile() throws TransformerException, ParserConfigurationException, IOException, SAXException;
}
