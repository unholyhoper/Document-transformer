package com.infor;

import com.infor.util.FileTransformerFactory;
import com.infor.util.transformer.AbstractFileTransformer;
import com.infor.util.transformer.TextFileTransformer;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class TextFileTransformationTest {

    ClassLoader classLoader = getClass().getClassLoader();
    public final static String BASE_PATH = "./";

    @Test
    public void testSimpleTextTransformationTest() {
        AbstractFileTransformer abstractFileTransformer = FileTransformerFactory.createFileTransformer(BASE_PATH + "manifesto.txt");
        assert (abstractFileTransformer instanceof TextFileTransformer);
    }

    @Test
    public void testShortTextTransformationTest() throws ParserConfigurationException, IOException, TransformerException, SAXException {
        URL inputURL = classLoader.getResource("manifesto.txt");
        String resourceFilePath = new File(inputURL.getFile()).getAbsolutePath();
        AbstractFileTransformer abstractFileTransformer = FileTransformerFactory.createFileTransformer(resourceFilePath, "priority", "not priority");
        abstractFileTransformer.transformFile();
    }

    @Test
    public void testLongTextTransformationTest() throws ParserConfigurationException, IOException, TransformerException, SAXException {
        URL inputURL = classLoader.getResource("longtexts/LongTextInput.txt");
        String inputFilePath = new File(inputURL.getFile()).getAbsolutePath();
        AbstractFileTransformer abstractFileTransformer = FileTransformerFactory.createFileTransformer(inputFilePath, "at", "zy");
        abstractFileTransformer.transformFile();

        URL expectedURL = classLoader.getResource("longtexts/LongTextExpected.txt");
        String expectedFilePath = new File(expectedURL.getFile()).getAbsolutePath();

        URL outputURL = classLoader.getResource("longtexts/result.txt");
        String outputFilePath = new File(outputURL.getFile()).getAbsolutePath();

        File expectedFile = new File(expectedFilePath);
        File outputFile = new File(outputFilePath);
        var expectedStream = new FileInputStream(expectedFile);
        var outputStream = new FileInputStream(outputFile);
        assertTrue(IOUtils.contentEquals(expectedStream, outputStream));
    }

    @Test
    public void doubleTransformationTest() throws ParserConfigurationException, IOException, TransformerException, SAXException {
        URL inputURL = classLoader.getResource("double-transformation/OriginalText.txt");
        String inputFilePath = new File(inputURL.getFile()).getAbsolutePath();

        String firstTransformationFile = inputFilePath.replace("OriginalText.txt", "FirstTransformation.txt");
        AbstractFileTransformer abstractFileTransformer = FileTransformerFactory.createFileTransformer(inputFilePath, firstTransformationFile, "the", "that");

        abstractFileTransformer.transformFile();

        String secondTransformationFile = inputFilePath.replace("OriginalText.txt", "SecondTransformation.txt");
        abstractFileTransformer = FileTransformerFactory.createFileTransformer(firstTransformationFile, secondTransformationFile, "that", "the");
        abstractFileTransformer.transformFile();

        URL originalURL = classLoader.getResource("double-transformation/OriginalText.txt");
        String originalFilePath = new File(originalURL.getFile()).getAbsolutePath();

        URL secondTransformationURL = classLoader.getResource("double-transformation/SecondTransformation.txt");
        String secondTransformationFilePath = new File(secondTransformationURL.getFile()).getAbsolutePath();

        File originalFile = new File(originalFilePath);
        File outputFile = new File(secondTransformationFilePath);
        var originalStream = new FileInputStream(originalFile);
        var secondStream = new FileInputStream(outputFile);
        assertTrue(IOUtils.contentEquals(originalStream, secondStream));
    }

    //TODO delete files
}
