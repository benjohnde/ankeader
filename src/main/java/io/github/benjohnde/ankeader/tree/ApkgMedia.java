package io.github.benjohnde.ankeader.tree;

import io.github.benjohnde.ankeader.parser.utils.MapUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

public class ApkgMedia {
    private String tmp;
    private Map<String, String> media;

    public ApkgMedia(Map<String, String> media, String tmp) {
        this.media = media;
        this.tmp = tmp;
    }

    public Path getMediaPath(String value) {
        String key = MapUtils.getKeyByValue(media, value);
        return Paths.get(tmp, key);
    }
}
