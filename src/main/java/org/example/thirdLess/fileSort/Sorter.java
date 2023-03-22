package org.example.thirdLess.fileSort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorter {
    public File sortFile(File dataFile) throws IOException {
        int partsNumber = 100;
        Path path = Paths.get(dataFile.toURI());
        long countLines = Files.lines(path).count();
        int numRows = (int) (countLines/partsNumber);
        File result = sortOneTime(dataFile, numRows);
        Validator validator = new Validator(result);
        while (!validator.isSorted()) {
            numRows = numRows*2;
            result = sortOneTime(result,numRows);
        }
        return result;
    }
    public File sortOneTime(File dataFile, int numRows) throws IOException {
        ReadFile rf = new ReadFile(dataFile.getName());
        List<File> files = rf.splitFile(dataFile.getName(), numRows, "part");

        //  sort parts
        for (int i = 0; i < files.size(); i++) {

            List<String> partLines = Files.readAllLines(Paths.get(files.get(i).getAbsolutePath()));
            List<Long> partLonges = new ArrayList<>();
            for (String str:partLines){
                partLonges.add(Long.parseLong(str));
            }
            Collections.sort(partLonges);
            File partFile = new File("part" + i + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(partFile));
            for (Long l: partLonges) {
                writer.write(String.valueOf(l));
                writer.newLine();
            }
            writer.close();
        }
        // Merge parts
        List<Long> sortedLines = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            List<String> partLines = Files.readAllLines(Paths.get("part" + i + ".txt"));
            for (String line:partLines) {
                sortedLines.add(Long.parseLong(line));
            }
        }
        // Write sorted lines to file
        File newFile = new File("sortedFile.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
        for (Long l : sortedLines) {
            writer.write(String.valueOf(l));
            writer.newLine();
        }

        writer.close();
        // Delete parts
        for (int i = 0; i < files.size(); i++) {
            File partFile = new File("part" + i + ".txt");
            partFile.delete();
        }

        return newFile;
    }

}
