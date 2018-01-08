package io.github.benjohnde.ankeader.parser;

import io.github.benjohnde.ankeader.anki.ApkgBase;
import io.github.benjohnde.ankeader.anki.ApkgCard;
import io.github.benjohnde.ankeader.anki.ApkgMedia;
import io.github.benjohnde.ankeader.parser.orm.CardEntity;
import io.github.benjohnde.ankeader.parser.orm.CollectionReader;
import io.github.benjohnde.ankeader.parser.utils.FileUtils;
import io.github.benjohnde.ankeader.parser.utils.JsonUtils;
import io.github.benjohnde.ankeader.parser.utils.ZipUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApkgReader {
    private String input, output;
    private File tmp;

    private ApkgMedia media;
    private List<CardEntity> cards;

    private ApkgBase base;

    public ApkgReader(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public void run() throws Exception {
        tmp = FileUtils.serve(this.input);
        System.out.println("Operating on: " + tmp);
        ZipUtils.unzip(this.input, tmp);
        readMedia();
        processMedia();
        readSqlite();
        prepareApkgBase();
        processApkgBase();
        FileUtils.remove(tmp);
    }

    private void processApkgBase() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (ApkgCard card : this.base.getCards()) {
            String qst = card.getQuestion();
            qst = qst.replaceAll("<img src=\"", "<img src=\"media/");

            String asw = card.getAnswer();
            asw = asw.replaceAll("<img src=\"", "<img src=\"media/");
            sb.append("<h4>#" + card.getTags() + "</h4>");
            sb.append("<h3>Frage</h3>");
            sb.append(qst);
            sb.append("<h3>Antwort</h3>");
            sb.append(asw);
            sb.append("<hr>");
        }

        String finalHtml = sb.toString();
        File output = new File(this.output, "index.html");
        FileWriter fooWriter = new FileWriter(output);
        fooWriter.write(finalHtml);
        fooWriter.close();
    }

    private void prepareApkgBase() {
        List<ApkgCard> apkgCards = new ArrayList<>();
        for (CardEntity cardEntity : this.cards) {
            apkgCards.add(new ApkgCard(cardEntity));
        }
        this.base = new ApkgBase(apkgCards, this.media);
    }

    private void readMedia() throws IOException {
        String jsonFile = new String(Files.readAllBytes(Paths.get(tmp.getAbsolutePath(), "media")));
        Map<String, String> media = JsonUtils.getMapFromJson(jsonFile);
        this.media = new ApkgMedia(media, tmp.getAbsolutePath());
    }

    private void processMedia() {
        this.media.getMedia().forEach((k, v) -> {
            File output = new File(this.output, "media");
            output.mkdirs();

            Path src = Paths.get(tmp.getAbsolutePath(), k);
            Path dest = Paths.get(output.getAbsolutePath(), v);
            try {
                Files.move(src, dest);
            } catch (IOException e) {
                // file may be not present
            }
        });
    }

    private void readSqlite() throws Exception {
        CollectionReader collectionReader = new CollectionReader(tmp.getAbsolutePath());
        this.cards = collectionReader.getCards();
    }
}
