package com.akatkar.files.forkJoin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.RecursiveAction;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by Ali Katkar on 12/9/2017.
 */
class DocumentCopyAction extends RecursiveAction {
    private final Document document;
    private final String suffix;
    private final File dest;


    DocumentCopyAction(Document document, String suffix, String dest) {
        super();
        this.document = document;
        this.suffix = suffix;
        this.dest = new File(dest);
    }

    @Override
    protected void compute(){
        //System.out.println(Thread.currentThread().getName());
        try {
            if (document.isSuffix(suffix)){
                Path src = document.getFile().toPath();
                Path dst = new File(dest, document.getFile().getName()).toPath();

                //System.out.println("copy:"+ document.getFile().toPath());
                Files.copy(src,dst, REPLACE_EXISTING);
            }
        } catch (IOException e) {
        }
    }
}