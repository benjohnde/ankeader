package io.github.benjohnde.ankeader;

import io.github.benjohnde.ankeader.parser.ApkgReader;

public class Ankeader {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Ankeader...");
        System.out.println("");

        String input = "/Users/katja/Desktop/ankeader/SIP1_bis_2017-09b.apkg";
        String output = "/Users/katja/Desktop/ankeader/document/";

        ApkgReader reader = new ApkgReader(input, output);
    }
}
