package com.akatkar.files.forkJoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Ali Katkar
 */
public class Document {
private final File file;

    public Document(File file) {
        this.file = file;
    }

    private String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    public Long search(String searchedWord) throws IOException {
        long count = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();
            while (line != null) {
                for (String word : wordsIn(line)) {
                    if (searchedWord.equals(word)) {
                        count = count + 1;
                    }
                }
                line = reader.readLine();
            }
        }
        return count;
    }

    public Long size() {
        return file.length();
    }

    public boolean isSuffix(String suffix){
        return this.file.getName().endsWith(suffix);
    }

    public File getFile(){
        return this.file;
    }    
}
