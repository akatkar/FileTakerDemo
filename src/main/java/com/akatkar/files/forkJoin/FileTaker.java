package com.akatkar.files.forkJoin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author Ali Katkar
 */
public class FileTaker implements Runnable {

    private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    private final String src;
    private final String dst;
    private final String suffix;

    public FileTaker(String src, String suffix, String dst) {
        this.src = src;
        this.dst = dst;
        this.suffix = suffix;
    }

    @Override
    public void run() {
        Folder folder;
        try {
            folder = Folder.fromDirectory(new File(src));
            FORK_JOIN_POOL.invoke(new FolderCopyAction(folder, suffix, dst));
        } catch (IOException ex) {
        }
    }
}
