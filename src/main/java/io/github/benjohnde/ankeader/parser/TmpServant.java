package io.github.benjohnde.ankeader.parser;

import java.io.File;
import java.io.IOException;

public class TmpServant {
    public static File serve(String dirName) throws IOException {
        File file = File.createTempFile(dirName, "");
        // create temporary file, delete it to retrieve tmp path
        file.delete();
        // create temporary directory
        file.mkdir();
        return file;
    }
}
