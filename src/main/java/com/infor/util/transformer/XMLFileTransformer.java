package com.infor.util.transformer;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


public class XMLFileTransformer extends AbstractFileTransformer {


    public XMLFileTransformer(File inputFile, File outputFile) {
        super(inputFile, outputFile);
    }

    public XMLFileTransformer(File inputFile, File outputFile,String searchText,String replaceText) {
        super(inputFile, outputFile,searchText,replaceText);
    }

    @Override
    public void transformFile() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        String replaceText = getReplaceText();
        String searchText = getSearchText();


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        Element root = doc.getDocumentElement();
        checkAndReplaceAttributeValue(root, searchText, replaceText);


        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(new DOMSource(doc), new StreamResult(outputFile));
    }

    private void checkAndReplaceAttributeValue(Node node, String searchText, String replaceText) {
        var attributes = node.getAttributes();
        for (int i = 0; attributes != null && i < attributes.getLength(); i++) {
            var attribute = attributes.item(i);
            if (searchText.equals(attribute.getNodeValue())) {
                attribute.setNodeValue(replaceText);
            }
        }
        var childNodes = node.getChildNodes();
        for (int j = 0; j < childNodes.getLength(); j++) {
            checkAndReplaceAttributeValue(childNodes.item(j), searchText, replaceText);
        }
    }
}
