package com.richie.nhdownloader.file;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Richie-PC
 */
public class Zip {

    public static void pack(String sourceDirPath, String zipFilePath) throws IOException {
        Path p = Files.createFile(Paths.get(zipFilePath));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

    public static void main(String[] args) throws IOException {
        Dotenv dotenv = Dotenv.load();
        
        String file = "C:\\Users\\Richie-PC\\Downloads\\nh_downloader";
        String code = "\\371274";
//        pack(file.concat(code + ".zip"), file.concat(code + ".zip"));

    }
}
