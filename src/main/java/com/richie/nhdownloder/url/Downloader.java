/*
 * Richie Zakaria
 */
package com.richie.nhdownloder.url;

import com.richie.nhdownloder.scraper.Scraper;
import com.sun.corba.se.impl.ior.ByteBuffer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Richie-PC
 */
public class Downloader extends Scraper {

    private final String HOME = System.getProperty("user.home");
    private final String DOWNLOAD_HOME = HOME + "/Downloads/" + "nh_downloader/";

    public Downloader(Document d, String c) {
        super(d, c);
    }

    public void startDownload() throws IOException {
        String[] url = super.getPicturesURL();
        for (int i = 0; i <= url.length; i++) {
            this.downloadImage(url[i], "a");
        }
    }

    public void downloadImage(String url, String fileName) throws IOException {
        Response resultImageResponse = Jsoup.connect(url)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .ignoreContentType(true).execute();
//        FileOutputStream out = (new FileOutputStream(new java.io.File(DOWNLOAD_HOME + fileName)));
//        out.write(resultImageResponse.bodyAsBytes());  // resultImageResponse.body() is where the image's contents are.
//        out.close();
        this.saveFile(resultImageResponse.bodyAsBytes(), fileName);
    }

    public void saveFile(byte[] b, String fileName) {
        File nhDir = new File(DOWNLOAD_HOME);
        if (!nhDir.exists()) {
            boolean created = nhDir.mkdir();
            if (!created) {
                System.out.println("Error while creating directory for location- " + nhDir);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FetchHTML fh = new FetchHTML(new CodeValidator("371274").toString());
        Downloader dwnl = new Downloader(fh.main(), "371274");
        dwnl.downloadImage("https://i.nhentai.net/galleries/1997899/1.jpg", "test.jpg");
    }
}
