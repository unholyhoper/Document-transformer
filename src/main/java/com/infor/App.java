package com.infor;

import com.infor.util.FileTransformerFactory;
import com.infor.util.transformer.AbstractFileTransformer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        System.out.println("Hello World!");
        String fileName = args[0];
        String searchText = args[1];
        String replaceText = args[2];
        AbstractFileTransformer fileTransformer;

        fileTransformer = FileTransformerFactory.createFileTransformer(fileName);
        //TODO add to factory method
        fileTransformer.setSearchText(searchText);
        fileTransformer.setReplaceText(replaceText);

        fileTransformer.transformFile();
    }
}
