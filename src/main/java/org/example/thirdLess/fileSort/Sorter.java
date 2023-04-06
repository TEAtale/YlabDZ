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

        Path path = Paths.get(dataFile.toURI());
        long countLines = Files.lines(path).count();
        if (countLines != 1){
            int partsNumber = 1;

            if (countLines > 1 && countLines < 1000) {
                partsNumber = 2;
            }
            else { partsNumber = 2;}
            int numRows = (int) (countLines/partsNumber);
            File result = sortOneTime(dataFile, numRows);

            return result;
        }
        return dataFile;
    }
    public File sortOneTime(File dataFile, int numRows) throws IOException {
        ReadFile rf = new ReadFile(dataFile.getName());
        List<File> files = rf.splitFile(numRows);
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
        // Write sorted lines to file
        File newFile = new File("sortedFile.txt");
        mergeSortedFiles(files, newFile);
        // Delete parts
        for (int i = 0; i < files.size(); i++) {
            File partFile = new File("part" + i + ".txt");
            partFile.deleteOnExit();
        }
        return newFile;
    }
    public void mergeSortedFiles(List<File> sorted, File output) {
        List<BufferedReader> readers = new ArrayList<>();
        List<Long> longs = new ArrayList<>();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            // Открываем BufferedReader для каждого файла и добавляем их в список
            for (File file : sorted) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                readers.add(reader);
            }
            // Пока есть открытые BufferedReader, считываем следующую строку из каждого файла
            // и записываем минимальную в выходной файл
            while (!readers.isEmpty()) {
                long minValue;
                BufferedReader minValueBR = null;
                for (BufferedReader reader : readers) {
                    String line = reader.readLine();
                    if (line != null) {
                        long value = Long.parseLong(line);
                        longs.add(value);
                        // Записываем минимальное значение в выходной файл
                    } else {
                        // Если строк больше нет, закрываем BufferedReader
                        reader.close();
                        // Удаляем его из списка
                        readers.remove(reader);
                        break;
                    }
                }
                Collections.sort(longs);
                minValue = longs.get(0);
                writer.write(String.valueOf(longs.get(0)));
                writer.newLine();
                longs.remove(minValue);
            }
            if (!longs.isEmpty()) {
                for (Long l:longs) {
                    writer.write(String.valueOf(l));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*while (!readers.isEmpty()) {
                long minValue = Long.MAX_VALUE;
                //BufferedReader minValueBR = null;
                for (BufferedReader reader : readers) {
                    String line = reader.readLine();
                    if (line != null) {
                        long value = Long.parseLong(line);
                        if (value < minValue) {
                            minValue = value;
                        }
                        else {
                            sortedL.add(value);
                        }
                        //longs.add(value);
                        // Записываем минимальное значение в выходной файл
                    } else {
                        // Если строк больше нет, закрываем BufferedReader
                        reader.close();
                        // Удаляем его из списка
                        readers.remove(reader);
                        break;
                    }
                }
                writer.write(String.valueOf(minValue));
                writer.newLine();
                break;
            }
           /* if (!longs.isEmpty()) {
                //Collections.sort(longs);
                for (Long l:longs) {
                    writer.write(String.valueOf(l));
                    writer.newLine();
                }
            }*/
// Закрываем все BufferedReader и BufferedWriter
          /*  for (Long l:sortedL) {
                    writer.write(String.valueOf(l));
                    writer.newLine();
                    }
                    } catch (IOException e) {
                    e.printStackTrace();
                    }
                    }*/