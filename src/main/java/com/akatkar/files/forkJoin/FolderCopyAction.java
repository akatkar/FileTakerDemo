package com.akatkar.files.forkJoin;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Ali Katkar on 12/9/2017.
 */
public class FolderCopyAction extends RecursiveAction {
    private final Folder folder;
    private final String suffix;
    private final String dest;

    FolderCopyAction(Folder folder, String suffix, String dest) {
        super();
        this.folder = folder;
        this.suffix = suffix;
        this.dest = dest;
    }

    @Override
    protected void compute() {
        ForkJoinTask.invokeAll(createSubTasks());
    }

    private List<RecursiveAction> createSubTasks(){
        List<RecursiveAction> tasks = new LinkedList<>();

        for (Folder subFolder : folder.getSubFolders()) {
            tasks.add(new FolderCopyAction(subFolder, suffix, dest));
        }
        for (Document document : folder.getDocuments()) {
            tasks.add(new DocumentCopyAction(document, suffix, dest));
        }
        return tasks;
    }
}
