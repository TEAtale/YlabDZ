package org.example.thirdLess.fileSort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    private BufferedReader br;
    private List<File> files = new ArrayList<>();

    ReadFile(String fileName) {
        File file = new File(fileName);
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }
    private String writeSplitFile(String splittedFileName, int numRows, String textLine) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(splittedFileName));
        for (int i = 0; i < numRows && textLine != null; i++) {
            writer.write(textLine + "\n");
            textLine = br.readLine();
        }
        writer.close();
        return textLine;
    }

    List<File> splitFile(String fileName, int numRows, String theFile) {
        String st;
        int numFiles = 0;

        try {
            String extensionFile = ".txt";

            if (br != null) {
                st = br.readLine();
                while (st != null) {
                    String newName = theFile + numFiles + ".txt";
                    st = writeSplitFile(newName, numRows, st);
                    files.add(new File(newName));
                    numFiles++;
                }
                br.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return files;
    }
}
