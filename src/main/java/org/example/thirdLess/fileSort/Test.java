package org.example.thirdLess.fileSort;

import java.io.File;
import java.io.IOException;


public class Test {
    public static void main(String[] args) throws IOException {
        File dataFile = new Generator().generate("data.txt", 10_000);
        System.out.println(new Validator(dataFile).isSorted()); // false
        File sortedFile = new Sorter().sortFile(dataFile);
        System.out.println(new Validator(sortedFile).isSorted()); // true
        File sortedByChunk = new SorterByChunk().sortFile(dataFile);
        System.out.println(new Validator(sortedByChunk).isSorted());
    }

}
