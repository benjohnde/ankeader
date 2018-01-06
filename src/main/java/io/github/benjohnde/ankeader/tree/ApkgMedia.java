package io.github.benjohnde.ankeader.tree;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ApkgMedia {
    private String tmp;
    private Map<String, String> media;

    public ApkgMedia(Map<String, String> media, String tmp) {
        this.media = media;
        this.tmp = tmp;
    }

    public Path getMediaPath(String key) {
        String value = media.get(key);
        return Paths.get(tmp, value);
    }
}
