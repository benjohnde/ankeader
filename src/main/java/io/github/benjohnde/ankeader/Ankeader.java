package io.github.benjohnde.ankeader;

import io.github.benjohnde.ankeader.anki.ApkgBase;
import io.github.benjohnde.ankeader.generator.DocGenerator;
import io.github.benjohnde.ankeader.parser.ApkgReader;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

public class Ankeader {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Ankeader...");
        System.out.println("");

        String home = System.getProperty("user.home");

        String input = Paths.get(home, "Desktop/ankeader/SIP1_bis_2017-09b.apkg").toString();
        String output = Paths.get(home, "Desktop/ankeader/document/").toString();

        ApkgReader reader = new ApkgReader(input, output);
        reader.run();

        ApkgBase base = reader.getBase();
        DocGenerator docGen = new DocGenerator(base);
        String doc = docGen.gen();

        File fileOutput = new File(output, "index.html");
        FileWriter fooWriter = new FileWriter(fileOutput);
        fooWriter.write(doc);
        fooWriter.close();
    }
}
