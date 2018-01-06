package io.github.benjohnde.ankeader;

import io.github.benjohnde.ankeader.parser.ApkgReader;

public class Ankeader {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Ankeader...");
        System.out.println("");

        ApkgReader reader = new ApkgReader("/Users/bjohn/git/ankeader/test/SIP1_bis_2017-09b.apkg");
    }
}
