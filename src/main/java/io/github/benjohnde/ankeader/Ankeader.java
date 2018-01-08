package io.github.benjohnde.ankeader;

import io.github.benjohnde.ankeader.anki.ApkgCard;
import io.github.benjohnde.ankeader.generator.DocGenerator;
import io.github.benjohnde.ankeader.parser.ApkgReader;
import io.github.benjohnde.ankeader.parser.utils.FileUtils;
import io.github.benjohnde.ankeader.transformer.AnkiTransformer;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class Ankeader {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Ankeader...");
        System.out.println("");

        String home = System.getProperty("user.home");

        String input = Paths.get(home, "Desktop/ankeader/SIP1_bis_2017-09b.apkg").toString();
        String output = Paths.get(home, "Desktop/ankeader/document/").toString();

        ApkgReader reader = new ApkgReader(input);
        reader.run();

        AnkiTransformer transformer = new AnkiTransformer();
        List<ApkgCard> cards = transformer.transformCards(reader.getCards());
        transformer.transformMedia(reader.getMedia(), input, output);

        DocGenerator docGen = new DocGenerator(cards);
        String doc = docGen.gen();

        File docFile = new File(output, "index.html");
        FileUtils.save(docFile, doc);

        reader.cleanup();
    }
}
