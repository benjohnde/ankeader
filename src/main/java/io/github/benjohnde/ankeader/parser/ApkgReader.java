package io.github.benjohnde.ankeader.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.benjohnde.ankeader.tree.ApkgMedia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ApkgReader {
    private String filename;
    private File tmp;
    private ApkgMedia media;

    public ApkgReader(String filename) throws Exception {
        this.filename = filename;
        tmp = TmpServant.serve(filename);
        System.out.println("Operating on: " + tmp);
        unzip();
        prepareApkgBase();
        readMedia();
        readSqlite();
        buildApkgTree();
        removeTmp();
    }

    private void removeTmp() throws IOException {
        Path rootPath = Paths.get(tmp.getAbsolutePath());
        Files.walk(rootPath, FileVisitOption.FOLLOW_LINKS)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    public void prepareApkgBase() {

    }

    public void readMedia() throws IOException {
        String jsonFile = new String(Files.readAllBytes(Paths.get(tmp.getAbsolutePath(), "media")));
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> media = new Gson().fromJson(jsonFile, type);
        this.media = new ApkgMedia(media, tmp.getAbsolutePath());
        System.out.println(this.media.getMediaPath("f804bea8a1a40b19360b44d40ed1bcb9.jpg"));
    }

    public void readSqlite() throws Exception {
        CollectionReader collectionReader = new CollectionReader(tmp.getAbsolutePath());
    }

    public void buildApkgTree() {

    }

    public void unzip() throws IOException {
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(filename));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            String fileName = zipEntry.getName();
            File newFile = new File(tmp.getAbsolutePath(), fileName);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
}
