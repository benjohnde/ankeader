package io.github.benjohnde.ankeader.transformer;

import io.github.benjohnde.ankeader.anki.ApkgCard;
import io.github.benjohnde.ankeader.parser.orm.CardEntity;
import io.github.benjohnde.ankeader.parser.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnkiTransformer {
    public List<ApkgCard> transformCards(List<CardEntity> cards) {
        return cards
                .stream()
                .map(card -> {
                    List<String> tags = Arrays.asList(card.getTags().trim().split(" "));
                    tags = Collections.unmodifiableList(tags);

                    List<String> slices = StringUtils.sliceBy((char) 0x1f, card.getFlds());
                    String question = slices.get(0);
                    String answer = slices.get(1);

                    return new ApkgCard(tags, answer, question);
                })
                .collect(Collectors.toList());
    }

    public void transformMedia(Map<String, String> media, String sourcePath, String destPath) {
        media.forEach((id, filename) -> {
            File outputDirectory = new File(destPath, "media");
            outputDirectory.mkdirs();

            Path src = Paths.get(sourcePath, id);
            Path dest = Paths.get(outputDirectory.getAbsolutePath(), filename);
            try {
                Files.move(src, dest);
            } catch (IOException e) {
                // file may be not present
            }
        });
    }
}
