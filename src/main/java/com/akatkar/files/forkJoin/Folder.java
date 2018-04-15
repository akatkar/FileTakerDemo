package com.akatkar.files.forkJoin;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ali Katkar
 */
public class Folder {
    private final List<Folder> subFolders;
    private final List<Document> documents;

    Folder(List<Folder> subFolders, List<Document> documents) {
        this.subFolders = subFolders;
        this.documents = documents;
    }

    List<Folder> getSubFolders() {
        return this.subFolders;
    }

    List<Document> getDocuments() {
        return this.documents;
    }

    static Folder fromDirectory(File dir) throws IOException {
        List<Document> documents = new LinkedList<>();
        List<Folder> subFolders = new LinkedList<>();
        for (File entry : dir.listFiles()) {
            if (entry.isDirectory()) {
                subFolders.add(Folder.fromDirectory(entry));
            } else {
                documents.add(new Document(entry));
            }
        }
        return new Folder(subFolders, documents);
    }


    public Long search(String searchedWord) throws IOException{
        long count = 0;
        for (Folder subFolder : getSubFolders()) {
            count = count + subFolder.search(searchedWord);
        }
        for (Document document : getDocuments()) {
            count = count + document.search(searchedWord);
        }
        return count;
    }
}
