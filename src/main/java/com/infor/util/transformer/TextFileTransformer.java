package com.infor.util.transformer;

import java.io.*;

public class TextFileTransformer extends AbstractFileTransformer {


    public TextFileTransformer(File inputFile, File outputFile) {
        super(inputFile, outputFile);
    }

    public TextFileTransformer(File inputFile, File outputFile, String searchText, String replaceText) {
        super(inputFile, outputFile, searchText, replaceText);
    }

    @Override
    public void transformFile() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            String previousLine = null;
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (previousLine != null) {
                    bufferedWriter.newLine();
                }
                currentLine = currentLine.replace(searchText, replaceText);
                bufferedWriter.write(currentLine);
                previousLine = currentLine;
            }
        }


    }
}
