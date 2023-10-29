package com.infor;

import com.infor.exception.FileWithoutExtensionException;
import com.infor.exception.UnsupportedFileExtensionException;
import com.infor.util.FileTransformerFactory;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class FileExtensionTest extends TestCase {

    public final static String BASE_PATH = "./";

    @Test(expected = UnsupportedFileExtensionException.class)
    public void testUnsupportedExtension() {
        Exception exception = assertThrows(UnsupportedFileExtensionException.class, () -> {
            FileTransformerFactory.createFileTransformer(BASE_PATH + "unsupportedFile.un");
        });

        String expectedMessage = "Extension .un unsupported";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testTextExtension() {
        FileTransformerFactory.createFileTransformer(BASE_PATH + "configuration.xml");
    }

    @Test
    public void testXMLExtension() {
        FileTransformerFactory.createFileTransformer(BASE_PATH + "manifesto.txt");
    }

    @Test
    public void testFileWithoutExtension() {
        Exception exception = assertThrows(FileWithoutExtensionException.class, () -> {
            FileTransformerFactory.createFileTransformer(BASE_PATH + "NoExtension");
        });
    }

}
