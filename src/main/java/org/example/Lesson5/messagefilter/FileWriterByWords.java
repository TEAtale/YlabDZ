package org.example.Lesson5.messagefilter;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileWriterByWords {
    public static void main(String[] args) throws IOException {
        File words = new File("src/main/resources/words.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(words), StandardCharsets.UTF_8));
        String line;
        File newFile = new File("NewWords.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split(", ");
                for (String str:lines) {
                    writer.write(str);
                    writer.newLine();
                }
            }
        }
        reader.close();
    }
}
