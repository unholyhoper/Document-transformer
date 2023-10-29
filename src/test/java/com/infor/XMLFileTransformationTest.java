package com.infor;

import com.infor.util.FileTransformerFactory;
import com.infor.util.transformer.AbstractFileTransformer;
import com.infor.util.transformer.TextFileTransformer;
import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class XMLFileTransformationTest {

    ClassLoader classLoader = getClass().getClassLoader();
    public final static String BASE_PATH = "./";


    @Ignore
    @Test
    public void testLongTextTransformationTest() throws ParserConfigurationException, IOException, TransformerException, SAXException {
        URL inputURL = classLoader.getResource("xml/configuration.xml");
        String inputFilePath = new File(inputURL.getFile()).getAbsolutePath();
        AbstractFileTransformer abstractFileTransformer = FileTransformerFactory.createFileTransformer(inputFilePath, "trace", "debug");
        abstractFileTransformer.transformFile();

        URL expectedURL = classLoader.getResource("xml/expected.xml");
        String expectedFilePath = new File(expectedURL.getFile()).getAbsolutePath();

        URL outputURL = classLoader.getResource("xml/result.xml");
        String outputFilePath = new File(outputURL.getFile()).getAbsolutePath();

        File expectedFile = new File(expectedFilePath);
        File outputFile = new File(outputFilePath);
        var expectedStream = new FileInputStream(expectedFile);
        var outputStream = new FileInputStream(outputFile);
        assertTrue(IOUtils.contentEquals(expectedStream, outputStream));
    }

}
