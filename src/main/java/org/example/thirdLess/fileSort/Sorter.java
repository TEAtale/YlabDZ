package org.example.thirdLess.fileSort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorter {
    public File sortFile(File dataFile) throws IOException {
        int partsNumber = 10;
        List<String> lines = Files.readAllLines(Paths.get(dataFile.getAbsolutePath()));
        int linesPerPart = lines.size() / partsNumber;
        // Create and sort parts
        for (int i = 0; i < partsNumber; i++) {
            File partFile = new File("part" + i + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(partFile));

            List<String> partLines = new ArrayList<>();
            List<Long> partLonges = new ArrayList<>();
            if (i == partsNumber - 1) {
                partLines.addAll(lines.subList(i * linesPerPart, lines.size()));
                for (String str:partLines){
                    partLonges.add(Long.parseLong(str));
                }
            } else {
                partLines.addAll(lines.subList(i * linesPerPart, (i + 1) * linesPerPart));
                for (String str:partLines){
                    partLonges.add(Long.parseLong(str));
                }
            }

            Collections.sort(partLonges);
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

        // Delete parts
        for (int i = 0; i < partsNumber; i++) {
            File partFile = new File("part" + i + ".txt");
            partFile.delete();
        }

        return newFile;
    }


}

/*public File sortFile(File dataFile) throws IOException {

        Path path = Paths.get(dataFile.toURI());
        long countLines = Files.lines(path).count();
        int len = (int) (countLines/10); //100
        System.out.println(countLines + " " + len); //1000
        File result = trySort(dataFile,len);
        Validator validator = new Validator(result);
        if (!validator.isSorted()) {
            len = len*2;
            result = trySort(result,len);
        }

        return result;
    }

    public File trySort (File dataFile, int len) throws IOException {
        long pointer = 0;
        File t1 = File.createTempFile("temp",".txt");


        try {
            long[] unsorted = new long[len];
            List<String> sorted = new ArrayList<>();
            RandomAccessFile rafReader = new RandomAccessFile(dataFile, "r");
            FileWriter writer = new FileWriter(t1, true);

            Path path = dataFile.toPath();
            long count = Files.lines(path).count();
            long number;
            String line;
            while ((line = rafReader.readLine()) != null) {

                //System.out.println(pointer);
                for (int j = 0; j < unsorted.length; j++) {
                    unsorted[j] = rafReader.readLong();
                }
                Arrays.sort(unsorted);

                for (int j = 0; j < unsorted.length; j++) {
                    sorted.add(String.valueOf(unsorted[j]));
                    //writer.write(unsorted[j] + "\n");
                }

                Files.write(Paths.get(t1.toURI()), sorted);
                //pointer = rafReader.getFilePointer();
                //count = count - pointer;
                sorted.clear();
                //rafReader.seek(pointer);
            }
            t1.deleteOnExit();
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Path path1 = Paths.get(t1.toURI());
        long count1 = Files.lines(path1).count();
        System.out.println(count1);

        return t1;
    }
*/
/*long pointer = 0;
        List<File> files = new ArrayList<>();

        try {
            long[] unsorted = new long[len];
            List<String> sorted = new ArrayList<>();
            RandomAccessFile rafReader = new RandomAccessFile(dataFile, "r");

            //rafReader.seek(pointer);
            String line;
            while ((line = rafReader.readLine()) != null) {

                //System.out.println(pointer);
                for (int j = 0; j < unsorted.length; j++) {
                    unsorted[j] = rafReader.readLong();
                }
                Arrays.sort(unsorted);
                File t1 = File.createTempFile("temp",".txt");
                FileWriter writer = new FileWriter(t1, false);
                for (int j = 0; j < unsorted.length; j++) {
                    sorted.add(String.valueOf(unsorted[j]));
                    writer.write(unsorted[j] + "\n");
                }
                files.add(t1);
                //Files.write(Paths.get(t1.toURI()), sorted);
                pointer = rafReader.getFilePointer();
                sorted.clear();
                rafReader.seek(pointer);
                t1.deleteOnExit();
                writer.flush();
                writer.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Path path1 = Paths.get(t1.toURI());
        //long count1 = Files.lines(path1).count();
        //System.out.println(count1);*/