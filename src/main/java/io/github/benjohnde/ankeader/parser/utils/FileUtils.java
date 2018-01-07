package io.github.benjohnde.ankeader.parser.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class FileUtils {
    public static File serve(String dirName) throws IOException {
        File file = File.createTempFile(dirName, "");
        // create temporary file, delete it to retrieve tmp path
        file.delete();
        // create temporary directory
        file.mkdir();
        return file;
    }

    public static void remove(File tmp) throws IOException {
        Path rootPath = Paths.get(tmp.getAbsolutePath());
        Files.walk(rootPath, FileVisitOption.FOLLOW_LINKS)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
