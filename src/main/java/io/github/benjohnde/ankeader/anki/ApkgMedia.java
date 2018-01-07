package io.github.benjohnde.ankeader.anki;

import io.github.benjohnde.ankeader.parser.utils.MapUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ApkgMedia {
    private final String tmp;
    private final Map<String, String> media;

    public ApkgMedia(Map<String, String> media, String tmp) {
        this.media = media;
        this.tmp = tmp;
    }

    public Path getMediaPath(String value) {
        String key = MapUtils.getKeyByValue(media, value);
        return Paths.get(tmp, key);
    }
}
