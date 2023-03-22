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
        for (int i = 0; i < partsNumber; i++) {
            List<String> partLines = Files.readAllLines(Paths.get("part" + i + ".txt"));
            for (String line:partLines) {
                sortedLines.add(Long.parseLong(line));
            }
        }

        Collections.sort(sortedLines);

        // Write sorted lines to file
        File newFile = new File("sortedFile.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
        for (Long l : sortedLines) {
            writer.write(String.valueOf(l));
            writer.newLine();
        }

        writer.close();
       /* Validator validator = new Validator(newFile);
        if (!validator.isSorted()) {
            sortFile(newFile);
        }
        else { return newFile;}*/

        // Delete parts
        for (int i = 0; i < partsNumber; i++) {
            File partFile = new File("part" + i + ".txt");
            partFile.delete();
        }

        return newFile;
    }


}
