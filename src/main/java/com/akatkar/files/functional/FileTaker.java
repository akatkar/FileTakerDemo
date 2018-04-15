package com.akatkar.files.functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author Ali Katkar
 */
public class FileTaker implements Runnable {

    private final String src;
    private final String dst;
    private final String suffix;

    public FileTaker(String src, String suffix, String dst) {
        this.src = src;
        this.dst = dst;
        this.suffix = suffix;
    }

    private boolean isSuffix(Path path) {
        return Files.isRegularFile(path) && path.toString().endsWith(suffix);
    }

    private void copy(Path srcPath) {
        try {
            Path dstPath = Paths.get(dst, srcPath.getFileName().toString());
            Files.copy(srcPath, dstPath, REPLACE_EXISTING);
        } catch (IOException e) {
        }
    }

    @Override
    public void run() {
        try {
            Files.walk(Paths.get(src))
                    .parallel()
                    .filter(this::isSuffix)
                    .forEach(this::copy);
        } catch (IOException ex) {
        }
    }
}
