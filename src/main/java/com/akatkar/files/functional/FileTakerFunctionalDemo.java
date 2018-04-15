package com.akatkar.files.functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Ali Katkar
 */
public class FileTakerFunctionalDemo {

    private static final String SRC = "D:\\Backup\\USB_1\\Calibre";
    private static final String DEST = "D:\\Backup\\USB_1\\copyTest";
    private static final String SFX = "pdf";

    private static void verifyPaths() {
        // terminate if source folder does not exist
        Path src = Paths.get(SRC);
        if (Files.exists(src) == false) {
            System.err.println(src + " does not exists");
            System.exit(1);
        }

        // create dest folder if it does not exist
        Path dst = Paths.get(DEST);
        if (Files.exists(dst) == false) {
            try {
                Files.createDirectories(dst);
            } catch (IOException ex) {
            }
        }
    }

    public static void main(String[] args) {
        
        verifyPaths();

        System.out.println("Functional Demo is running ...");
        FileTaker fileTaker = new FileTaker(SRC, SFX, DEST);
        fileTaker.run();
    }
}
